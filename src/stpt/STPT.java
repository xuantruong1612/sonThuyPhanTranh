// 
// Decompiled by Procyon v0.5.36
// 

package stpt;

import network.Message;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

/**
 *
 * @author TRUONG
 */

public class STPT {
    public static void main(final String[] args) {
        // GameScr.init();
        final int pos = 19197;
        Server.start(pos);
    }

    public static byte[] getFile(final String url) {
        try {
            final FileInputStream openFileInput = new FileInputStream(url);
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] bArr = new byte[1024];
            while (true) {
                final int read = openFileInput.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            openFileInput.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected static void saveFile(final String url, final byte[] ab) {
        try {
            final File f = new File(url);
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();
            final FileOutputStream fos = new FileOutputStream(url);
            fos.write(ab);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeByteArray(final Message msg, final byte[] ab) {
        try {
            msg.writer().writeInt(ab.length);
            msg.writer().write(ab);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
