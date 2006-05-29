/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.homeunix.drummer.model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.homeunix.drummer.model.Accounts;
import org.homeunix.drummer.model.Categories;
import org.homeunix.drummer.model.DataModel;
import org.homeunix.drummer.model.ModelPackage;
import org.homeunix.drummer.model.Transactions;

import org.homeunix.drummer.model.Types;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.homeunix.drummer.model.impl.DataModelImpl#getAllTransactions <em>All Transactions</em>}</li>
 *   <li>{@link org.homeunix.drummer.model.impl.DataModelImpl#getAllCategories <em>All Categories</em>}</li>
 *   <li>{@link org.homeunix.drummer.model.impl.DataModelImpl#getAllAccounts <em>All Accounts</em>}</li>
 *   <li>{@link org.homeunix.drummer.model.impl.DataModelImpl#getAllTypes <em>All Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataModelImpl extends EObjectImpl implements DataModel {
	/**
	 * The cached value of the '{@link #getAllTransactions() <em>All Transactions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllTransactions()
	 * @generated
	 * @ordered
	 */
	protected Transactions allTransactions = null;

	/**
	 * The cached value of the '{@link #getAllCategories() <em>All Categories</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllCategories()
	 * @generated
	 * @ordered
	 */
	protected Categories allCategories = null;

	/**
	 * The cached value of the '{@link #getAllAccounts() <em>All Accounts</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllAccounts()
	 * @generated
	 * @ordered
	 */
	protected Accounts allAccounts = null;

	/**
	 * The cached value of the '{@link #getAllTypes() <em>All Types</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllTypes()
	 * @generated
	 * @ordered
	 */
	protected Types allTypes = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ModelPackage.eINSTANCE.getDataModel();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Categories getAllCategories() {
		return allCategories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAllCategories(Categories newAllCategories, NotificationChain msgs) {
		Categories oldAllCategories = allCategories;
		allCategories = newAllCategories;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.DATA_MODEL__ALL_CATEGORIES, oldAllCategories, newAllCategories);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAllCategories(Categories newAllCategories) {
		if (newAllCategories != allCategories) {
			NotificationChain msgs = null;
			if (allCategories != null)
				msgs = ((InternalEObject)allCategories).eInverseRemove(this, ModelPackage.CATEGORIES__ALL_CATEGORIES, Categories.class, msgs);
			if (newAllCategories != null)
				msgs = ((InternalEObject)newAllCategories).eInverseAdd(this, ModelPackage.CATEGORIES__ALL_CATEGORIES, Categories.class, msgs);
			msgs = basicSetAllCategories(newAllCategories, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DATA_MODEL__ALL_CATEGORIES, newAllCategories, newAllCategories));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Types getAllTypes() {
		return allTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAllTypes(Types newAllTypes, NotificationChain msgs) {
		Types oldAllTypes = allTypes;
		allTypes = newAllTypes;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.DATA_MODEL__ALL_TYPES, oldAllTypes, newAllTypes);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAllTypes(Types newAllTypes) {
		if (newAllTypes != allTypes) {
			NotificationChain msgs = null;
			if (allTypes != null)
				msgs = ((InternalEObject)allTypes).eInverseRemove(this, ModelPackage.TYPES__ALL_TYPES, Types.class, msgs);
			if (newAllTypes != null)
				msgs = ((InternalEObject)newAllTypes).eInverseAdd(this, ModelPackage.TYPES__ALL_TYPES, Types.class, msgs);
			msgs = basicSetAllTypes(newAllTypes, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DATA_MODEL__ALL_TYPES, newAllTypes, newAllTypes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transactions getAllTransactions() {
		return allTransactions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAllTransactions(Transactions newAllTransactions, NotificationChain msgs) {
		Transactions oldAllTransactions = allTransactions;
		allTransactions = newAllTransactions;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.DATA_MODEL__ALL_TRANSACTIONS, oldAllTransactions, newAllTransactions);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAllTransactions(Transactions newAllTransactions) {
		if (newAllTransactions != allTransactions) {
			NotificationChain msgs = null;
			if (allTransactions != null)
				msgs = ((InternalEObject)allTransactions).eInverseRemove(this, ModelPackage.TRANSACTIONS__ALL_TRANSACTIONS, Transactions.class, msgs);
			if (newAllTransactions != null)
				msgs = ((InternalEObject)newAllTransactions).eInverseAdd(this, ModelPackage.TRANSACTIONS__ALL_TRANSACTIONS, Transactions.class, msgs);
			msgs = basicSetAllTransactions(newAllTransactions, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DATA_MODEL__ALL_TRANSACTIONS, newAllTransactions, newAllTransactions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Accounts getAllAccounts() {
		return allAccounts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAllAccounts(Accounts newAllAccounts, NotificationChain msgs) {
		Accounts oldAllAccounts = allAccounts;
		allAccounts = newAllAccounts;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.DATA_MODEL__ALL_ACCOUNTS, oldAllAccounts, newAllAccounts);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAllAccounts(Accounts newAllAccounts) {
		if (newAllAccounts != allAccounts) {
			NotificationChain msgs = null;
			if (allAccounts != null)
				msgs = ((InternalEObject)allAccounts).eInverseRemove(this, ModelPackage.ACCOUNTS__ALL_ACCOUNTS, Accounts.class, msgs);
			if (newAllAccounts != null)
				msgs = ((InternalEObject)newAllAccounts).eInverseAdd(this, ModelPackage.ACCOUNTS__ALL_ACCOUNTS, Accounts.class, msgs);
			msgs = basicSetAllAccounts(newAllAccounts, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.DATA_MODEL__ALL_ACCOUNTS, newAllAccounts, newAllAccounts));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case ModelPackage.DATA_MODEL__ALL_TRANSACTIONS:
					if (allTransactions != null)
						msgs = ((InternalEObject)allTransactions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DATA_MODEL__ALL_TRANSACTIONS, null, msgs);
					return basicSetAllTransactions((Transactions)otherEnd, msgs);
				case ModelPackage.DATA_MODEL__ALL_CATEGORIES:
					if (allCategories != null)
						msgs = ((InternalEObject)allCategories).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DATA_MODEL__ALL_CATEGORIES, null, msgs);
					return basicSetAllCategories((Categories)otherEnd, msgs);
				case ModelPackage.DATA_MODEL__ALL_ACCOUNTS:
					if (allAccounts != null)
						msgs = ((InternalEObject)allAccounts).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DATA_MODEL__ALL_ACCOUNTS, null, msgs);
					return basicSetAllAccounts((Accounts)otherEnd, msgs);
				case ModelPackage.DATA_MODEL__ALL_TYPES:
					if (allTypes != null)
						msgs = ((InternalEObject)allTypes).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.DATA_MODEL__ALL_TYPES, null, msgs);
					return basicSetAllTypes((Types)otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case ModelPackage.DATA_MODEL__ALL_TRANSACTIONS:
					return basicSetAllTransactions(null, msgs);
				case ModelPackage.DATA_MODEL__ALL_CATEGORIES:
					return basicSetAllCategories(null, msgs);
				case ModelPackage.DATA_MODEL__ALL_ACCOUNTS:
					return basicSetAllAccounts(null, msgs);
				case ModelPackage.DATA_MODEL__ALL_TYPES:
					return basicSetAllTypes(null, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ModelPackage.DATA_MODEL__ALL_TRANSACTIONS:
				return getAllTransactions();
			case ModelPackage.DATA_MODEL__ALL_CATEGORIES:
				return getAllCategories();
			case ModelPackage.DATA_MODEL__ALL_ACCOUNTS:
				return getAllAccounts();
			case ModelPackage.DATA_MODEL__ALL_TYPES:
				return getAllTypes();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ModelPackage.DATA_MODEL__ALL_TRANSACTIONS:
				setAllTransactions((Transactions)newValue);
				return;
			case ModelPackage.DATA_MODEL__ALL_CATEGORIES:
				setAllCategories((Categories)newValue);
				return;
			case ModelPackage.DATA_MODEL__ALL_ACCOUNTS:
				setAllAccounts((Accounts)newValue);
				return;
			case ModelPackage.DATA_MODEL__ALL_TYPES:
				setAllTypes((Types)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ModelPackage.DATA_MODEL__ALL_TRANSACTIONS:
				setAllTransactions((Transactions)null);
				return;
			case ModelPackage.DATA_MODEL__ALL_CATEGORIES:
				setAllCategories((Categories)null);
				return;
			case ModelPackage.DATA_MODEL__ALL_ACCOUNTS:
				setAllAccounts((Accounts)null);
				return;
			case ModelPackage.DATA_MODEL__ALL_TYPES:
				setAllTypes((Types)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case ModelPackage.DATA_MODEL__ALL_TRANSACTIONS:
				return allTransactions != null;
			case ModelPackage.DATA_MODEL__ALL_CATEGORIES:
				return allCategories != null;
			case ModelPackage.DATA_MODEL__ALL_ACCOUNTS:
				return allAccounts != null;
			case ModelPackage.DATA_MODEL__ALL_TYPES:
				return allTypes != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //DataModelImpl
