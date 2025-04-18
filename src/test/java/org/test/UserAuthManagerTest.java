package org.test;

import org.example.UserAuthManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserAuthManagerTest {
    @Test
    public void testHasAccess() {
        UserAuthManager admin = new UserAuthManager("adminUser", "admin");
        assertTrue(admin.hasAccess("editResource"));
        assertTrue(admin.hasAccess("viewResource"));

        UserAuthManager editor = new UserAuthManager("editorUser", "editor");
        assertTrue(editor.hasAccess("editResource"));
        assertFalse(editor.hasAccess("viewResource"));

        UserAuthManager viewer = new UserAuthManager("viewerUser", "viewer");
        assertFalse(viewer.hasAccess("editResource"));
        assertTrue(viewer.hasAccess("viewResource"));

        UserAuthManager invalidRole = new UserAuthManager("invalidUser", "invalid");
        assertFalse(invalidRole.hasAccess("editResource"));
        assertFalse(invalidRole.hasAccess("viewResource"));

        assertThrows(IllegalArgumentException.class, () -> {
            admin.hasAccess("");
        });
    }

    @Test
    public void testCanPerformOperation() {
        UserAuthManager admin = new UserAuthManager("adminUser", "admin");
        assertTrue(admin.canPerformOperation("edit"));
        assertTrue(admin.canPerformOperation("delete"));
        assertTrue(admin.canPerformOperation("view"));

        UserAuthManager editor = new UserAuthManager("editorUser", "editor");
        assertTrue(editor.canPerformOperation("edit"));
        assertTrue(editor.canPerformOperation("delete"));
        assertFalse(editor.canPerformOperation("view"));

        UserAuthManager viewer = new UserAuthManager("viewerUser", "viewer");
        assertFalse(viewer.canPerformOperation("edit"));
        assertFalse(viewer.canPerformOperation("delete"));
        assertTrue(viewer.canPerformOperation("view"));

        UserAuthManager invalidRole = new UserAuthManager("invalidUser", "invalid");
        assertFalse(invalidRole.canPerformOperation("edit"));
        assertFalse(invalidRole.canPerformOperation("delete"));
        assertFalse(invalidRole.canPerformOperation("view"));

        assertThrows(IllegalArgumentException.class, () -> {
            admin.canPerformOperation("");
        });
    }

    @Test
    public void testMeetsCondition() {
        UserAuthManager admin = new UserAuthManager("adminUser", "admin");
        assertTrue(admin.meetsCondition("isAdmin"));
        assertFalse(admin.meetsCondition("isEditor"));
        assertFalse(admin.meetsCondition("isViewer"));

        UserAuthManager editor = new UserAuthManager("editorUser", "editor");
        assertFalse(editor.meetsCondition("isAdmin"));
        assertTrue(editor.meetsCondition("isEditor"));
        assertFalse(editor.meetsCondition("isViewer"));

        UserAuthManager viewer = new UserAuthManager("viewerUser", "viewer");
        assertFalse(viewer.meetsCondition("isAdmin"));
        assertFalse(viewer.meetsCondition("isEditor"));
        assertTrue(viewer.meetsCondition("isViewer"));

        assertFalse(admin.meetsCondition("invalidCondition"));

        assertThrows(IllegalArgumentException.class, () -> {
            admin.meetsCondition("");
        });
    }
}