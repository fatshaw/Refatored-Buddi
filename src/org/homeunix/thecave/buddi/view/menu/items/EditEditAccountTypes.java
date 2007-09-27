/*
 * Created on Aug 6, 2007 by wyatt
 */
package org.homeunix.thecave.buddi.view.menu.items;

import java.awt.event.ActionEvent;

import org.homeunix.thecave.buddi.i18n.keys.MenuKeys;
import org.homeunix.thecave.buddi.model.prefs.PrefsModel;
import org.homeunix.thecave.buddi.view.AccountTypeListFrame;
import org.homeunix.thecave.buddi.view.MainFrame;
import org.homeunix.thecave.moss.exception.WindowOpenException;
import org.homeunix.thecave.moss.swing.MossMenuItem;
import org.homeunix.thecave.moss.util.Log;

public class EditEditAccountTypes extends MossMenuItem{
	public static final long serialVersionUID = 0;
	
	//This has to be a AccountFrame, as we need to get selected accounts.
	public EditEditAccountTypes(MainFrame frame) {
		super(frame, PrefsModel.getInstance().getTranslator().get(MenuKeys.MENU_EDIT_EDIT_ACCOUNT_TYPES));
	}
	
	@Override
	public void updateMenus() {
		super.updateMenus();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		((MainFrame) getFrame()).getDocument().startBatchChange();

		try {
			new AccountTypeListFrame((MainFrame) getFrame()).openWindow();
		}
		catch (WindowOpenException woe){
			Log.error(woe);
		}
		
		((MainFrame) getFrame()).getDocument().finishBatchChange();
	}
}