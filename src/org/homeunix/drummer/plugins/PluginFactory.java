/*
 * Created on Sep 14, 2006 by wyatt
 */
package org.homeunix.drummer.plugins;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.jar.JarEntry;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.roydesign.ui.JScreenMenuItem;

import org.homeunix.drummer.Const;
import org.homeunix.drummer.controller.MainBuddiFrame;
import org.homeunix.drummer.controller.Translate;
import org.homeunix.drummer.plugins.BuddiPluginHelper.DateChoice;
import org.homeunix.drummer.plugins.BuddiPluginHelper.DateRangeType;
import org.homeunix.drummer.plugins.BuddiPluginImpl.BuddiExportPluginImpl;
import org.homeunix.drummer.plugins.BuddiPluginImpl.BuddiImportPluginImpl;
import org.homeunix.drummer.plugins.BuddiPluginImpl.BuddiPanelPluginImpl;
import org.homeunix.drummer.plugins.interfaces.BuddiExportPlugin;
import org.homeunix.drummer.plugins.interfaces.BuddiImportPlugin;
import org.homeunix.drummer.plugins.interfaces.BuddiPanelPlugin;
import org.homeunix.drummer.plugins.interfaces.BuddiPlugin;
import org.homeunix.drummer.prefs.Plugin;
import org.homeunix.drummer.prefs.PrefsInstance;
import org.homeunix.drummer.view.AbstractFrame;
import org.homeunix.drummer.view.components.CustomDateDialog;
import org.homeunix.thecave.moss.jar.JarLoader;
import org.homeunix.thecave.moss.util.Log;


public class PluginFactory<T extends BuddiPlugin> {

	public static List<JScreenMenuItem> getExportMenuItems(final AbstractFrame frame){
		List<JScreenMenuItem> exportMenuItems = new LinkedList<JScreenMenuItem>();

		PluginFactory<BuddiExportPlugin> factory = new PluginFactory<BuddiExportPlugin>();
		List<BuddiExportPlugin> exportPlugins = factory.getPluginObjects(BuddiExportPluginImpl.class);

		//Iterate through each plugin, and create a menu item for each.
		for (BuddiExportPlugin plugin : exportPlugins) {
			JScreenMenuItem menuItem = new JScreenMenuItem(plugin.getDescription());

			//This is where the user's custom code is actually run
			addExportActionListener(plugin, menuItem, frame);

			exportMenuItems.add(menuItem);
		}

		return exportMenuItems;
	}

	public static List<JScreenMenuItem> getImportMenuItems(final AbstractFrame frame){
		List<JScreenMenuItem> importMenuItems = new LinkedList<JScreenMenuItem>();

		PluginFactory<BuddiImportPlugin> factory = new PluginFactory<BuddiImportPlugin>();
		List<BuddiImportPlugin> importPlugins = factory.getPluginObjects(BuddiImportPluginImpl.class);

		//Iterate through each plugin, and create a menu item for each.
		for (BuddiImportPlugin plugin : importPlugins) {
			JScreenMenuItem menuItem = new JScreenMenuItem(plugin.getDescription());

			//This is where the user's custom code is actually run
			addImportActionListener(plugin, menuItem, frame);

			importMenuItems.add(menuItem);
		}

		return importMenuItems;
	}

	public static List<JPanel> getPanelLaunchers(){
		List<JPanel> panelItems = new LinkedList<JPanel>();

		PluginFactory<BuddiPanelPlugin> factory = new PluginFactory<BuddiPanelPlugin>();
		List<BuddiPanelPlugin> panelPlugins = factory.getPluginObjects(BuddiPanelPluginImpl.class);

		//Iterate through each plugin, and create a menu item for each.
		for (BuddiPanelPlugin plugin : panelPlugins) {
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

			//Select the correct options for the dropdown, based on the plugin
			Vector<DateChoice> dateChoices;
			if (plugin.getDateRangeType().equals(DateRangeType.INTERVAL))
				dateChoices = BuddiPluginHelper.getInterval();
			else if (plugin.getDateRangeType().equals(DateRangeType.START_ONLY))
				dateChoices = BuddiPluginHelper.getStartOnly();
			else if (plugin.getDateRangeType().equals(DateRangeType.END_ONLY))
				dateChoices = BuddiPluginHelper.getEndOnly();
			else
				dateChoices = new Vector<DateChoice>();

			//Get the combobox 
			final JComboBox dateSelect = new JComboBox(dateChoices);
			dateSelect.setPreferredSize(new Dimension(Math.max(150, dateSelect.getPreferredSize().width), dateSelect.getPreferredSize().height));

			//Add the launchers
			addPanelActionListener(plugin, dateSelect);

			panel.add(new JLabel(Translate.getInstance().get(plugin.getDescription())));
			panel.add(dateSelect);

			panelItems.add(panel);
		}

		return panelItems;
	}
	
	/**
	 * Returns a list of all the class names (in filesystem format, 
	 * i.e. org/homeunix/drummer/Test.class) of classes which
	 * implement the BuddiPlugin interface.  This is a relatively
	 * expensive operation - we have to instantiate each class
	 * within the jar file to be sure it is the correct type -
	 * so use this method with care. 
	 * @param jarFile
	 * @return
	 */
	public static Vector<String> getAllPluginsFromJar(File jarFile){
		Vector<String> classNames = new Vector<String>();
		
		
		for (JarEntry entry : JarLoader.getAllClasses(jarFile)) {
			try {
				if (Const.DEVEL) Log.debug("Loading " + entry.getName());
				Object o = JarLoader.getObject(jarFile, filesystemToClass(entry.getName()));
				if (o instanceof BuddiPlugin)
					classNames.add(entry.getName());
			}
			catch (Exception e){}
		}
		
		return classNames;
	}


	/**
	 * Returns a list of plugin objects, after instantiating and checking 
	 * type.
	 * @param pluginType The class of the type of objects required.  This is needed to allow type checking on a generic.
	 * @return List of plugin objects of type pluginType.
	 */
	@SuppressWarnings("unchecked")
	private List<T> getPluginObjects(Class<? extends T> pluginTypeClass){
		List<T> pluginEntries = new LinkedList<T>();

		Object pluginType;
		try{
			pluginType = pluginTypeClass.newInstance();
		}
		catch(Exception e){
			Log.critical("Cannot create plugin type object to compare instances against.  I cannot load any plugins:" + e);
			return pluginEntries;
		}

		try{
			for (String pluginClass : Const.BUILT_IN_PLUGINS) {
				Object pluginObject = Class.forName(pluginClass).newInstance();

				if (pluginType instanceof BuddiExportPlugin
						&& pluginObject instanceof BuddiExportPlugin){
					if (Const.DEVEL) Log.info("Adding Export Plugin " + pluginObject);
					pluginEntries.add((T) pluginObject);
				}
				else if (pluginType instanceof BuddiImportPlugin
						&& pluginObject instanceof BuddiImportPlugin){
					if (Const.DEVEL) Log.info("Adding Import Plugin " + pluginObject);
					pluginEntries.add((T) pluginObject);
				}
				else if (pluginType instanceof BuddiPanelPlugin
						&& pluginObject instanceof BuddiPanelPlugin){
					if (Const.DEVEL) Log.info("Adding Panel Plugin " + pluginObject);
					pluginEntries.add((T) pluginObject);
				}
			}
		}
		catch(Exception e){
			Log.warning("Loading built in plugins: " + e);
		}

		// TODO Currently we load every jar separately.  This can be slow - perhaps we should test first.
		for (Object o1 : PrefsInstance.getInstance().getPrefs().getLists().getPlugins()) {
			if (o1 instanceof Plugin){
				Plugin plugin = (Plugin) o1;
				
				File jarFile = new File(plugin.getJarFile());
				String className = plugin.getClassName();
				className = filesystemToClass(className);
				try{
					Object pluginObject = JarLoader.getObject(jarFile, className);

					if (pluginType instanceof BuddiExportPlugin
							&& pluginObject instanceof BuddiExportPlugin){
						pluginEntries.add((T) pluginObject);
					}
					else if (pluginType instanceof BuddiImportPlugin
							&& pluginObject instanceof BuddiImportPlugin){
						pluginEntries.add((T) pluginObject);
					}
					else if (pluginType instanceof BuddiPanelPlugin
							&& pluginObject instanceof BuddiPanelPlugin){
						pluginEntries.add((T) pluginObject);
					}
				}
				catch(Exception e){
					Log.warning("Loading custom plugins: " + e);
				}
			}
		}

		return pluginEntries;

	}

	private static void addExportActionListener(final BuddiExportPlugin plugin, JScreenMenuItem menuItem, final AbstractFrame frame){
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (plugin instanceof BuddiExportPlugin)
					((BuddiExportPlugin) plugin).exportData(frame);
			}
		});
	}

	private static void addImportActionListener(final BuddiImportPlugin plugin, JScreenMenuItem menuItem, final AbstractFrame frame){
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (plugin instanceof BuddiImportPlugin)
					((BuddiImportPlugin) plugin).importData(frame);
			}
		});
	}

	private static void addPanelActionListener(final BuddiPanelPlugin plugin, final JComboBox dateSelect){
		dateSelect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//Find out which item was clicked on
				Object o = dateSelect.getSelectedItem();

				//If the object was a date choice, access the encoded dates
				if (o instanceof DateChoice 
						&& plugin instanceof BuddiPanelPlugin){
					DateChoice choice = (DateChoice) o;

					//As long as the choice is not custom, our job is easy
					if (!choice.isCustom()){
						//Launch a new reports window with given parameters
						BuddiPluginHelper.openNewPanelPluginWindow(
								(BuddiPanelPlugin) plugin, 
								choice.getStartDate(), 
								choice.getEndDate()
						);
					}
					//If they want a custom window, it's a little 
					// harder... we need to open the custom date
					// window, which then launches the plugin.
					else{
						new CustomDateDialog(
								MainBuddiFrame.getInstance(),
								(BuddiPanelPlugin) plugin
						).openWindow();
					}
				}

				dateSelect.setSelectedItem(null);
			}
		});
	}
	
	/**
	 * Converts classes from filesystem type (i.e., org/homeunix/thecave/Test.class)
	 * to Class type (i.e., org.homeunix.thecave.Test).
	 * @param filesystemClassName class in Filesystem type
	 * @return
	 */
	private static String filesystemToClass(String filesystemClassName){
		return filesystemClassName.replaceAll(".class$", "").replaceAll("/", ".");
	}
}