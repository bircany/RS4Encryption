Bu konuda ki amacımız  RC4 kesintisiz şifrelemeyle anahtar zamanlama algoritmasını ve pseudo random oluşturma algoritması kullanarak şifreleme algoritması geliştirmek.

Öncelikle **RC4** , bir akış şifresi ve değişken uzunluklu anahtar algoritmasıdır. Bu algoritma her seferinde bir biti şifreler (veya bir seferde daha büyük birim verileri).

Normal RC4 şifresinin 4 çeşidi vardır:

1. **Spritz** — Spritz, kriptografik hash fonksiyonları ve deterministik rastgele bit üreteci oluşturmak için kullanılır.
2. **RC4A** — Bu, ortalama RC4 şifresinden daha hızlı ve daha güçlü olması önerilen bir varyanttır. RC4A’nın şifresinde kullanılan gerçek rastgele sayılara sahip değildir.
3. **VMPC** — Değişken Olarak Değiştirilmiş Permütasyon Kompozisyonu (VMPC), RC4A gibi, şifresinde gerçekten rastgele sayıların kullanılmadığı tespit edilen bir RC4 sürümüdür.
4. **RC4A+** — RC4A+, RC4A’nın RC4 ve RC4A’dan daha uzun ve daha karmaşık, ancak karmaşıklığının bir sonucu olarak daha güçlü olan gelişmiş bir sürümüdür.

RC4 ‘ ün avantajları ; 

RC4, diğer akış şifrelerine kıyasla bazı avantajlara sahiptir:

- RC4'ün kullanımı son derece basittir, dolayısıyla uygulamayı da basitleştirir.
- RC4, basitliği nedeniyle hızlıdır ve bu da onu daha iyi performans gösteren bir şifre yapar.
- RC4 ayrıca büyük veri akışlarıyla hızlı ve kolay bir şekilde çalışır.

RC4 ‘ ün dezavantajları ; 

- RC4'te bulunan güvenlik açıkları, RC4'ün son derece güvensiz olduğu anlamına gelir, bu nedenle artık çok az uygulama onu kullanır.
- RC4, daha küçük veri akışlarında kullanılamaz, bu nedenle kullanımı diğer akış şifrelerinden daha spesifiktir.
- RC4 ayrıca kimlik doğrulama sağlamaz, bu nedenle MİTM (man-in-the-middle) ortadaki adam saldırısı meydana gelebilir ve RC4 şifre kullanıcısı daha akıllı olamaz.

Bir anahtar girişi, giriş anahtarı bilgisi olmadan tahmin edilemeyen 8 bitlik bir akış numarası üreten sözde rasgele bit oluşturucudur. Oluşturucunun çıkışına anahtar akışı denir ve Xor kullanılarak düz metin akış şifresiyle her seferinde bir bayt birleştirilir. 

A:Bilgi 
B:Anahtar
C:xor sonucu çıkan veri 
A xor B = C
C xor B = A

# **Örnek:**
örneğin isminizi öğrenmek isteyen bir kullanıcıya kendi isminizi şifreleyerek göndermeye çalışıyorsunuz.
bircan isminin ikilik karşılığı 11010011 olsun. Bende ve kullanıcıda şifreyi çözen iki anahtar var. Xor kullanarak bilgiyi şifreleyip karşı tarafa göndereceğiz.
bircan: 11010011
key: 00111000 olsun

Not💡: Bende ve kullanıcıda kesinlikle aynı anahtar bulunmalıdır.

      11010011

00111000

(xor) ————————

sonuç: 11101011

benim tarafımdan 1100011xor00111000=11101011 sonucu çıkıyor peki bu ne anlama geliyor, bu gönderdiğimiz ismi ve anahtarı xor kapısından geçirerek karşı tarafa gönderdik. Karşı taraf ise çıkan sonucu gene aynı anahtar ile xor kapısından geçirerek benim ismimi elde edecek sonuç bu.

     11101011

     00111000

(xor) ———————-

     11010011

kullanıcı tarafı 11101011xor00111000=11010011 sonucunu elde ediyor ve bu da tekrar bircan ismini elde ediyor. Yani basit bir xor kapısıyla bilgiyi gizleyip ve gönderip tekrar karşı tarafta aynı anahtar kullanarak elde ediyoruz.
gönderen: bircan : 11010011
alıcı: kullanıcı: 11010011: bircan
en basit ve güvenli bilgi gönderme yöntemidir.

**Anahtar Oluşturma Algoritması –**

- S[0] ila S[255] öğeleriyle 256 baytlık bir durum vektörü S’yi başlatmak için 1 ila 256 bayt arasında değişken uzunlukta bir anahtar kullanılır.
- Şifreleme ve şifre çözme için, sistematik bir şekilde 255 girişten biri seçilerek S’den bir bayt k üretilir, ardından S’deki girişlere yeniden izin verilir.

**Anahtar Zamanlama Algoritması:**

- S’nin girişleri, artan sırada 0 ila 255 arasındaki değerlere eşit olarak ayarlanır,
- geçici bir vektör T oluşturulur. k anahtarının uzunluğu 256 bayt ise, k, T’ye atanır.
    - Aksi takdirde, uzunluk (k-len) bayt olan bir anahtar için, K’den kopyalandığı gibi T’nin ilk k-len öğeleri ve ardından K tekrarlanır. T’yi doldurmak için gerektiği kadar. Fikir şu şekilde gösterilmektedir:
        
        
        ```java
        for(int i=0; i<255 ;i++){
        	S[i] = i;
        	T[i] = K[i % (k — len)];
        }
        ```
        

## **Sözde rastgele oluşturma algoritması (Akış Oluşturma):**

S vektörü başlatıldığında, giriş tuşu kullanılmayacaktır. Bu adımda, her S[i] algoritması için, S’nin mevcut konfigürasyonu tarafından dikte edilen bir şemaya göre S’deki başka bir bayt ile değiştirin. S[255]’e ulaştıktan sonra işlem tekrar S[0]’dan başlayarak devam eder.

```java
i,j = 0;
while(true){
	i = (i+1) % 256;
	j = (j+S[i]) % 256;
	Swap(S[i] , S[j]);
	t = (S[i] + S[j] )% 256;
	k = S[t]

}
```

```java
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

```

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/8203a35e-3644-4bd9-9b77-c8fc9496e3b8/e23ef8b2-51be-4685-8a05-fa8108c8bf1d/image.png)

`display()` metoduyla bir tamsayı dizisini alıp her bir elemanını karakter olarak ekrana yazdırır. Bu, şifrelenmiş veya çözülen metni görüntülemek için kullanırız.

`stringToAsciiArray()` metodu Bir dizeyi (metin veya anahtar) karakterlerin ASCII değerlerinden oluşan bir tamsayı dizisine dönüştürür. Bu, RC4 algoritmasında kullanılacak girişlerin işlenmesini sağlar.

`initializeKeySchedule`() metodu 

**Key Scheduling Algorithm (KSA) anahtar zamanlama algoritması** adı verilen bir süreçtir:

- `s` dizisi 0'dan 255'e kadar olan tamsayılarla başlatılır.
- Anahtar kullanılarak `s` dizisi karıştırılır. Karışım işlemi, `key` dizisi boyunca döngüsel olarak gerçekleştirilir.

`process()` metodu 
Bu metot, hem şifreleme hem de şifre çözme için kullanılır:

- `input`: Şifrelenecek veya çözülecek metin.
- `output`: Sonuç (şifreli veya çözülen metin).
- `s` dizisi karıştırılır ve bir keystream oluşturulur.
- Keystream ile giriş verisi XOR işlemine tabi tutulur, böylece şifreli veya çözülen metin elde edilir.

`swap()` metodu İki dizin arasındaki değerleri yer değiştirir. Bu, KSA ve keystream oluşturma işlemlerinin bir parçasıdır.

`main`  metot’ta

- Kullanıcıdan düz metin ve anahtar alınır.
- Girişler ASCII dizilerine dönüştürülür.
- RC4 algoritması ile şifreleme ve şifre çözme gerçekleştirilir.
- Sonuçlar ekrana yazdırılır.

Resources
[https://www.youtube.com/watch?v=VUwiDh0m_kg&ab_channel=Donanımsal](https://www.youtube.com/watch?v=VUwiDh0m_kg&ab_channel=Donan%C4%B1msal)
https://www.geeksforgeeks.org/rc4-encryption-algorithm/
https://devrimozcay.medium.com/rc4-encryption-algorithm-rc4-%C5%9Fifreleme-algoritmas%C4%B1-b08786efebed
