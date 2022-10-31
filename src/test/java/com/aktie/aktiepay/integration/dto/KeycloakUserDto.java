package com.aktie.aktiepay.integration.dto;

public class KeycloakUserDto {

    private String id;

    private long createdTimestamp;

    private String username;

    private boolean enabled;

    private boolean totp;

    private boolean emailVerified;

    private Attributes attributes;

    private Object[] disableableCredentialTypes;

    private Object[] requiredActions;

    private int notBefore;

    private Access access;

    public KeycloakUserDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(long createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getTotp() {
        return totp;
    }

    public void setTotp(boolean totp) {
        this.totp = totp;
    }

    public boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public Object[] getDisableableCredentialTypes() {
        return disableableCredentialTypes;
    }

    public void setDisableableCredentialTypes(Object[] disableableCredentialTypes) {
        this.disableableCredentialTypes = disableableCredentialTypes;
    }

    public Object[] getRequiredActions() {
        return requiredActions;
    }

    public void setRequiredActions(Object[] requiredActions) {
        this.requiredActions = requiredActions;
    }

    public int getNotBefore() {
        return notBefore;
    }

    public void setNotBefore(int notBefore) {
        this.notBefore = notBefore;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }
}

class Access {
    private boolean manageGroupMembership;

    private boolean view;

    private boolean mapRoles;

    private boolean impersonate;

    private boolean manage;

    public Access() {
    }

    public boolean getManageGroupMembership() {
        return manageGroupMembership;
    }

    public void setManageGroupMembership(boolean manageGroupMembership) {
        this.manageGroupMembership = manageGroupMembership;
    }

    public boolean getView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public boolean getMapRoles() {
        return mapRoles;
    }

    public void setMapRoles(boolean mapRoles) {
        this.mapRoles = mapRoles;
    }

    public boolean getImpersonate() {
        return impersonate;
    }

    public void setImpersonate(boolean impersonate) {
        this.impersonate = impersonate;
    }

    public boolean getManage() {
        return manage;
    }

    public void setManage(boolean manage) {
        this.manage = manage;
    }
}

class Attributes {
    private String[] userId;

    public Attributes() {
    }

    public Attributes(String[] userId) {
        this.userId = userId;
    }

    public String[] getUserId() {
        return userId;
    }

    public void setUserId(String[] userId) {
        this.userId = userId;
    }
}