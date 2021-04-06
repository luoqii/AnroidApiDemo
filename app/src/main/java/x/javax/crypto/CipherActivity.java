package x.javax.crypto;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.bysong.android.apidemo.R;

import org.bbs.android.log.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CipherActivity extends AppCompatActivity {
    private String mPath;
    private String mPass;
    private Cipher mCipher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cipher);
    }

    void initParam(){
        mPath = ((TextView)findViewById(R.id.path)).getText().toString();
        mPass = ((TextView)findViewById(R.id.password)).getText().toString();
        mCipher = new Cipher(mPass.getBytes());
    }

    public void encrypt(View view) {
        initParam();

        byte[] data = FileIOUtils.readFile2BytesByChannel(mPath);
        byte[] encryptData = mCipher.encrypt(data);
        File f = new File(mPath + ".en");
        f.delete();
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileIOUtils.writeFileFromBytesByChannel(f, encryptData, true);

//        File en = new File(mPath + ".en");
//        mCipher.encryptFile(new File(mPath), en);
    }

    public void decrypt(View view) {
        initParam();

        byte[] data = FileIOUtils.readFile2BytesByChannel(mPath);
        byte[] encryptData = mCipher.decrypt(data);
        File f = new File(mPath + ".de");
        f.delete();
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileIOUtils.writeFileFromBytesByChannel(f, encryptData, true);
    }

    public static class Cipher {
        private static final String TAG = "Cipher";

        public static final String ALGORITHM_AES = "AES";
        public static final String AES_CBC = "AES/CBC/PKCS5Padding";
        private static final boolean DEBUG_LOCAL = false;
        private final byte[] mKey;
        private javax.crypto.Cipher mEncryptor;
        private javax.crypto.Cipher mDecryptor;
        private static final byte[] CBC_IV
                = new byte[]{0xa, 0xb, 0xc, 0xd, 0x1, 0x2, 0x3, 0x3, 0xa, 0xa, 0xa, 0xa, 0xf, 0xf, 0xf, 0xf};

        public Cipher(byte[] key){
            this(key, CBC_IV);
        }

        public Cipher(byte[] key, byte[] iv){
            mKey = key;

            if (DEBUG_LOCAL) {
                Log.d(TAG, "init with transformation:" + AES_CBC);
                Log.d(TAG, "init with key           :" + ConvertUtils.bytes2HexString(key));
                Log.d(TAG, "init with iv            :" + ConvertUtils.bytes2HexString(iv));
            }

            try {
                mEncryptor = javax.crypto.Cipher.getInstance(AES_CBC);
                SecretKeySpec secretKeySpec = new SecretKeySpec(mKey, ALGORITHM_AES);
                mEncryptor.init(javax.crypto.Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(iv));

                mDecryptor = javax.crypto.Cipher.getInstance(AES_CBC);
                secretKeySpec = new SecretKeySpec(mKey, ALGORITHM_AES);
                mDecryptor.init(javax.crypto.Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(iv));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            }
        }

        // FIXME not work
        public void encryptFile(File plainTextFile, File encryptedFile) {
            BufferedInputStream in = null;
            BufferedOutputStream out = null;
            try {
                in = new BufferedInputStream(new FileInputStream(plainTextFile));
                out = new BufferedOutputStream(new FileOutputStream(encryptedFile));
                encryptedFile.delete();
                encryptedFile.getParentFile().mkdir();
                encryptedFile.createNewFile();
                int count = 1024;
                byte[] buffer = new byte[32];
                byte[] en = new byte[1024];
                while ((count = in.read(buffer)) > 0) {
                    en = mEncryptor.doFinal(buffer, 0 , count);
                    out.write(en, 0, en.length);
                    out.flush();
                }
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // FIXME not work
        public void decryptFile(File encryptedFile, File plainTextFile){
            BufferedInputStream in = null;
            BufferedOutputStream out = null;
            try {
                plainTextFile.delete();
                plainTextFile.getParentFile().mkdir();
                plainTextFile.createNewFile();
                in = new BufferedInputStream(new FileInputStream(encryptedFile));
                out = new BufferedOutputStream(new FileOutputStream(plainTextFile));
                int count = 1024;
                byte[] buffer = new byte[1024];
                byte[] en = new byte[1024];
                while ((count = in.read(buffer)) > 0) {
                    en = mDecryptor.doFinal(buffer, 0 , count);
                    out.write(en, 0, en.length);
                }
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public byte[] encrypt(byte[] input){
            byte[] encrypted = null;
            try {
                if (DEBUG_LOCAL){
                    Log.d(TAG, "plain  text:" + ConvertUtils.bytes2HexString(input));
                }
                encrypted = mEncryptor.doFinal(input);
                if (DEBUG_LOCAL) {
                    Log.d(TAG, "cipher text:" + ConvertUtils.bytes2HexString(encrypted));
                }
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            }
            return encrypted;
        }

        public byte[] decrypt(byte[] input){
            byte[] decrypted = null;
            try {
                if (DEBUG_LOCAL) {
                    Log.d(TAG, "cipher text:" + ConvertUtils.bytes2HexString(input));
                }
                decrypted = mDecryptor.doFinal(input);
                if (DEBUG_LOCAL){
                    Log.d(TAG, "plain  text:" + ConvertUtils.bytes2HexString(decrypted));
                }
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            }
            return decrypted;
        }
    }
}
