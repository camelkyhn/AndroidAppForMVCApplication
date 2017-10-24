package com.example.kemal.androidappformvcapplication.DAL.Tables;

public class TokenTable
{
    private String TableName = "Token";
    private String access_token = "access_token";
    private String token_type = "token_type";
    private String expires_in = "expires_in";
    private String userName = "userName";
    private String issued = "issued";
    private String expires = "expires";

    public String createQuery()
    {
        return "CREATE TABLE '" + TableName + "' (" +
                access_token + " VARCHAR NOT NULL, " +
                token_type + " VARCHAR NOT NULL, " +
                expires_in + " INTEGER NOT NULL, " +
                userName + " VARCHAR NOT NULL, " +
                issued + " DATETIME NOT NULL, " +
                expires + " DATETIME NOT NULL);";
    }
    public String dropQuery() { return "DROP TABLE IF EXISTS '" + TableName + "';"; }

    public String getTableName() { return TableName; }

    public String getAccess_token() { return access_token; }

    public String getToken_type() { return token_type; }

    public String getExpires_in() { return expires_in; }

    public String getUserName() { return userName; }

    public String getIssued() { return issued; }

    public String getExpires() { return expires; }
}
