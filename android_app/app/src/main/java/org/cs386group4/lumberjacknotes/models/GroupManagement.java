package org.cs386group4.lumberjacknotes.models;

public class GroupManagement
{
    private String[] membersList;
    //private Permissions memberRole; Commented out for testing purposes
    private Boolean privacySettings;
    private String groupName;
    private int[] sharedNotesList;

    /**
     * TODO
     * This method allows a user to create a group
     * @param groupName Name to be given to new group
     */
    public void createGroup(String groupName)
    {
        // TODO
    }

    /**
     * TODO
     * This method allows a Group Administrator to delete a group
     */
    public void deleteGroup()
    {
        // TODO
    }

    /**
     * TODO
     * This method allows users in a group or Group Administrators to invite others to the current group my entering the
     * invitee's email
     * @param memberEmail User to be invited email
     */
    public void inviteMembers(String memberEmail)
    {
        // TODO
    }

    /**
     * TODO
     * This method allows users in a group or Group Administrators to remove a member from the current group
     * @param memberEmail User to be removed email
     */
    public void removeFromGroup(String memberEmail)
    {
        // TODO
    }

    /**
     * TODO
     * This method allows Group Administrator to rename an existing group
     * @param newGroupName New name to be given to group
     */
    public void renameGroup(String newGroupName)
    {
        // TODO
    }

//    // This method is commented out for testing purposes
//    /**
//     * This method allows a Group Administrator to set the role of another member
//     * @param newRole New role to be given to a group member
//     */
//    public void setMemberRole(Permissions newRole)
//    {
//
//    }

    /**
     * TODO
     * This method allows a Group Administrator to set the privacy status of a group
     * @param newPrivacySetting New privacy status for group (True = Public; False = Private)
     */
    public void setPrivacySettings(boolean newPrivacySetting)
    {
        // TODO
    }
}
