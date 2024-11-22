package com.napier.sem;

import com.sun.tools.jconsole.JConsoleContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{

    static App app;

    @Test
    void testConnection()
    {
        app = new App();
        app.connect("localhost:33060", 30000);
        assertNotNull(app.con);
    }

    @Test
    void testNullConnection()
    {
        app = new App();
        app.connect(null, 30000);
        assertNull(app.con);
    }

    @Test
    void testDisconnect() throws SQLException
    {
        app = new App();
        app.connect("localhost:33060", 30000);
        app.disconnect();
        assertTrue(app.con.isClosed());
    }

    @Test
    void  testNullDisconnect()
    {
        app = new App();
        app.con = null;
        ByteArrayOutputStream test = new ByteArrayOutputStream();
        System.setOut(new PrintStream(test));
        app.disconnect();
        assertEquals(test.toString(), "Connection is null");
    }
}