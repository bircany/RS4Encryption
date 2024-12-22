package rc4;

import java.util.Scanner;

class RC4 {

    static void display(int[] array) {
        for (int value : array) {
            System.out.println((char) value);
        }
    }
    static int[] stringToAsciiArray(String input) {
        char[] charArray = input.toCharArray();
        int[] asciiArray = new int[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            asciiArray[i] = charArray[i];
        }
        return asciiArray;
    }

    // Anahtar zamanlama algoritmasını (KSA) başlatır.
    static void initializeKeySchedule(int[] s, int[] key) {
        int j = 0;
        for (int i = 0; i < 256; i++) {
            s[i] = i;
        }
        for (int i = 0; i < 256; i++) {
            j = (j + s[i] + key[i % key.length]) % 256;
            swap(s, i, j);
        }
    }

    // Keystream oluşturur ve şifreleme veya şifre çözme işlemini gerçekleştirir (main algorithm)
    static void process(int[] s, int[] input, int[] output) {
        int i = 0, j = 0;
        for (int l = 0; l < input.length; l++) {
            i = (i + 1) % 256;
            j = (j + s[i]) % 256;
            swap(s, i, j);
            int z = s[(s[i] + s[j]) % 256];
            output[l] = input[l] ^ z;
        }
    }

    static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("\n Düz metni girin:");
        String plaintext = kb.nextLine();
        System.out.println("\n Anahtarı girin:");
        String key = kb.nextLine();

        // Kullanıcı tarafından girilen string türündeki bilgi ve anahtarı ASCII dizilerine dönüştür
        int[] plaintextAscii = stringToAsciiArray(plaintext);
        int[] keyAscii = stringToAsciiArray(key);

        // S dizisini başlat ve anahtar zamanlamasını gerçekleştir
        int[] s = new int[256];
        initializeKeySchedule(s, keyAscii);

        // Şifrele ve çöz  (kullanıcının girdiği string bilginin ascıı'ye donusturulmus uzunluğu kadar
        // şifrelenmiş metni ve deşifrelenmiş metni içerisine atayabileceğimiz iki array oluşturduk
        int[] ciphertext = new int[plaintextAscii.length];
        int[] decryptedText = new int[plaintextAscii.length];
        process(s.clone(), plaintextAscii, ciphertext);  //encryption
        process(s.clone(), ciphertext, decryptedText);   //decryption

        System.out.print("\n Şifrelenmiş metin:");
        display(ciphertext);
        System.out.print("\n Çözülmüş metin:");
        display(decryptedText);
        kb.close();
    }
}
