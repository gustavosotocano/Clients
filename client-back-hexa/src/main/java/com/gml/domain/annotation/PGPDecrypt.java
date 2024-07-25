package com.gml.domain.annotation;



public class PGPDecrypt {
//    static {
//        Security.addProvider(new BouncyCastleProvider());
//    }
//
//    public static void main(String[] args) {
//        try {
//            String encryptedFilePath = "path/to/encrypted/file.asc";
//            String privateKeyFilePath = "path/to/private/key.asc";
//            char[] passphrase = "your_passphrase".toCharArray();
//
//            InputStream encryptedData = new FileInputStream(encryptedFilePath);
//            InputStream privateKeyStream = new FileInputStream(privateKeyFilePath);
//
//            // Load private key
//            PGPPrivateKey pgpPrivateKey = getPrivateKey(privateKeyStream, passphrase);
//
//            // Decrypt the message
//            InputStream decryptedData = decrypt(encryptedData, pgpPrivateKey);
//
//            // Output the decrypted message
//            BufferedReader reader = new BufferedReader(new InputStreamReader(decryptedData));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static PGPPrivateKey getPrivateKey(InputStream keyIn, char[] passphrase) throws IOException, PGPException {
//        keyIn = PGPUtil.getDecoderStream(keyIn);
//
//        PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(keyIn, new JcaKeyFingerprintCalculator());
//
//        Iterator<PGPSecretKeyRing> keyRingIter = pgpSec.getKeyRings();
//        while (keyRingIter.hasNext()) {
//            PGPSecretKeyRing keyRing = keyRingIter.next();
//
//            Iterator<PGPSecretKey> keyIter = keyRing.getSecretKeys();
//            while (keyIter.hasNext()) {
//                PGPSecretKey key = keyIter.next();
//
//                if (key.isSigningKey()) {
//                    PGPPrivateKey privateKey = key.extractPrivateKey(
//                            new JcePBESecretKeyDecryptorBuilder().setProvider("BC").build(passphrase)
//                    );
//                    if (privateKey != null) {
//                        return privateKey;
//                    }
//                }
//            }
//        }
//        throw new IllegalArgumentException("No private key found in the provided key ring.");
//    }
//
//    private static InputStream decrypt(InputStream encryptedData, PGPPrivateKey privateKey) throws IOException, PGPException {
//        encryptedData = PGPUtil.getDecoderStream(encryptedData);
//
//        PGPObjectFactory pgpF = new PGPObjectFactory(encryptedData, new JcaKeyFingerprintCalculator());
//        PGPEncryptedDataList enc;
//        Object o = pgpF.nextObject();
//
//        if (o instanceof PGPEncryptedDataList) {
//            enc = (PGPEncryptedDataList) o;
//        } else {
//            enc = (PGPEncryptedDataList) pgpF.nextObject();
//        }
//
//        Iterator<PGPPublicKeyEncryptedData> it = enc.getEncryptedDataObjects();
//        PGPPrivateKey sKey = null;
//        PGPPublicKeyEncryptedData pbe = null;
//
//        while (sKey == null && it.hasNext()) {
//            pbe = it.next();
//            sKey = privateKey;
//        }
//
//        if (sKey == null) {
//            throw new IllegalArgumentException("Secret key for message not found.");
//        }
//
//        InputStream clear = pbe.getDataStream(new JcePublicKeyDataDecryptorFactoryBuilder().setProvider("BC").build(sKey));
//
//        PGPObjectFactory plainFact = new PGPObjectFactory(clear, new JcaKeyFingerprintCalculator());
//        Object message = plainFact.nextObject();
//
//        if (message instanceof PGPCompressedData) {
//            PGPCompressedData cData = (PGPCompressedData) message;
//            PGPObjectFactory pgpFact = new PGPObjectFactory(cData.getDataStream(), new JcaKeyFingerprintCalculator());
//            message = pgpFact.nextObject();
//        }
//
//        if (message instanceof PGPLiteralData) {
//            PGPLiteralData ld = (PGPLiteralData) message;
//            return ld.getInputStream();
//        } else if (message instanceof PGPOnePassSignatureList) {
//            throw new PGPException("Encrypted message contains a signed message - not literal data.");
//        } else {
//            throw new PGPException("Message is not a simple encrypted file.");
//        }
//    }
}
