// 
// Decompiled by Procyon v0.5.36
// 

package network;

/**
 *
 * @author TRUONG
 */

public interface IMessageHandler {
    void onConnectOK();

    void onConnectionFail();

    void onDisconnected();

    void onMessage(final Message p0);
}
