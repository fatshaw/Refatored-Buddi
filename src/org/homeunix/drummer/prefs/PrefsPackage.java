/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.homeunix.drummer.prefs;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.homeunix.drummer.prefs.PrefsFactory
 * @model kind="package"
 * @generated
 */
public interface PrefsPackage extends EPackage{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "prefs";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "urn:org.homeunix.drummer.prefs.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.homeunix.drummer.prefs";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PrefsPackage eINSTANCE = org.homeunix.drummer.prefs.impl.PrefsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.homeunix.drummer.prefs.impl.DictEntryImpl <em>Dict Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.homeunix.drummer.prefs.impl.DictEntryImpl
	 * @see org.homeunix.drummer.prefs.impl.PrefsPackageImpl#getDictEntry()
	 * @generated
	 */
	int DICT_ENTRY = 0;

	/**
	 * The feature id for the '<em><b>Entry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICT_ENTRY__ENTRY = 0;

	/**
	 * The number of structural features of the the '<em>Dict Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DICT_ENTRY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.homeunix.drummer.prefs.impl.PrefsImpl <em>Prefs</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.homeunix.drummer.prefs.impl.PrefsImpl
	 * @see org.homeunix.drummer.prefs.impl.PrefsPackageImpl#getPrefs()
	 * @generated
	 */
	int PREFS = 1;

	/**
	 * The feature id for the '<em><b>Data File</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFS__DATA_FILE = 0;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFS__LANGUAGE = 1;

	/**
	 * The feature id for the '<em><b>Show Deleted Accounts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFS__SHOW_DELETED_ACCOUNTS = 2;

	/**
	 * The feature id for the '<em><b>Show Deleted Categories</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFS__SHOW_DELETED_CATEGORIES = 3;

	/**
	 * The feature id for the '<em><b>Date Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFS__DATE_FORMAT = 4;

	/**
	 * The feature id for the '<em><b>Desc Dict</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFS__DESC_DICT = 5;

	/**
	 * The feature id for the '<em><b>Memo Dict</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFS__MEMO_DICT = 6;

	/**
	 * The number of structural features of the the '<em>Prefs</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFS_FEATURE_COUNT = 7;


	/**
	 * Returns the meta object for class '{@link org.homeunix.drummer.prefs.DictEntry <em>Dict Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dict Entry</em>'.
	 * @see org.homeunix.drummer.prefs.DictEntry
	 * @generated
	 */
	EClass getDictEntry();

	/**
	 * Returns the meta object for the attribute '{@link org.homeunix.drummer.prefs.DictEntry#getEntry <em>Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Entry</em>'.
	 * @see org.homeunix.drummer.prefs.DictEntry#getEntry()
	 * @see #getDictEntry()
	 * @generated
	 */
	EAttribute getDictEntry_Entry();

	/**
	 * Returns the meta object for class '{@link org.homeunix.drummer.prefs.Prefs <em>Prefs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Prefs</em>'.
	 * @see org.homeunix.drummer.prefs.Prefs
	 * @generated
	 */
	EClass getPrefs();

	/**
	 * Returns the meta object for the attribute '{@link org.homeunix.drummer.prefs.Prefs#getDataFile <em>Data File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data File</em>'.
	 * @see org.homeunix.drummer.prefs.Prefs#getDataFile()
	 * @see #getPrefs()
	 * @generated
	 */
	EAttribute getPrefs_DataFile();

	/**
	 * Returns the meta object for the attribute '{@link org.homeunix.drummer.prefs.Prefs#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Language</em>'.
	 * @see org.homeunix.drummer.prefs.Prefs#getLanguage()
	 * @see #getPrefs()
	 * @generated
	 */
	EAttribute getPrefs_Language();

	/**
	 * Returns the meta object for the attribute '{@link org.homeunix.drummer.prefs.Prefs#isShowDeletedAccounts <em>Show Deleted Accounts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Show Deleted Accounts</em>'.
	 * @see org.homeunix.drummer.prefs.Prefs#isShowDeletedAccounts()
	 * @see #getPrefs()
	 * @generated
	 */
	EAttribute getPrefs_ShowDeletedAccounts();

	/**
	 * Returns the meta object for the attribute '{@link org.homeunix.drummer.prefs.Prefs#isShowDeletedCategories <em>Show Deleted Categories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Show Deleted Categories</em>'.
	 * @see org.homeunix.drummer.prefs.Prefs#isShowDeletedCategories()
	 * @see #getPrefs()
	 * @generated
	 */
	EAttribute getPrefs_ShowDeletedCategories();

	/**
	 * Returns the meta object for the attribute '{@link org.homeunix.drummer.prefs.Prefs#getDateFormat <em>Date Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date Format</em>'.
	 * @see org.homeunix.drummer.prefs.Prefs#getDateFormat()
	 * @see #getPrefs()
	 * @generated
	 */
	EAttribute getPrefs_DateFormat();

	/**
	 * Returns the meta object for the containment reference list '{@link org.homeunix.drummer.prefs.Prefs#getMemoDict <em>Memo Dict</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Memo Dict</em>'.
	 * @see org.homeunix.drummer.prefs.Prefs#getMemoDict()
	 * @see #getPrefs()
	 * @generated
	 */
	EReference getPrefs_MemoDict();

	/**
	 * Returns the meta object for the containment reference list '{@link org.homeunix.drummer.prefs.Prefs#getDescDict <em>Desc Dict</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Desc Dict</em>'.
	 * @see org.homeunix.drummer.prefs.Prefs#getDescDict()
	 * @see #getPrefs()
	 * @generated
	 */
	EReference getPrefs_DescDict();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PrefsFactory getPrefsFactory();

} //PrefsPackage
