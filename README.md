# KinLanguage

KinLanguage, Türkçe tabanlı basit bir yorumlayıcı (interpreter)
dilidir.\
Amaç, temel programlama mantığını Türkçe sözdizimi ile göstermek.

------------------------------------------------------------------------

## ✨ Özellikler

-   **Değişken tanımlama** (`tam`)
-   **Koşullu ifadeler** (`eger`)
-   **Konsola çıktı verme** (`yazdir`)
-   **Artırma / Azaltma** (`+=`, `++`, `-=`, `--`)

------------------------------------------------------------------------

## 🚀 Örnek Kod

``` plaintext
tam round = 0
tam val = 5

round += 1

eger(round < val) {
    yazdir("aaaaaa")
}

round += 15

eger(round > val) {
    yazdir("buyuk")
}
```

### 📌 Beklenen Çıktı

``` plaintext
aaaaaa
buyuk
```

------------------------------------------------------------------------

## 🔑 Sözdizimi Açıklamaları

### 1. Değişkenler (`tam`)

Bir tam sayı değişkeni tanımlamak için `tam` kullanılır:

``` plaintext
tam skor = 10
```

Bu satır, `skor` isminde bir değişken oluşturur ve değerini `10` yapar.

------------------------------------------------------------------------

### 2. Koşullar (`eger`)

Koşullu ifadeler için `eger (şart) { ... }` yapısı kullanılır:

``` plaintext
eger(skor > 5) {
    yazdir("Yeterli skor!")
}
```

`eger` içindeki şart **doğruysa** süslü parantez içindeki kod çalışır.
Yanlışsa atlanır.

------------------------------------------------------------------------

### 3. Yazdırma (`yazdir`)

Konsola çıktı vermek için `yazdir` komutu kullanılır:

``` plaintext
yazdir("Merhaba Dünya")
```

Değişkenlerle birleştirme yapılabilir:

``` plaintext
tam adim = 5
yazdir("Adım sayısı: " + adim)
```

Çıktı:

``` plaintext
Adım sayısı: 5
```

------------------------------------------------------------------------

## 🛠 Geliştirme Durumu

-   [x] Değişken tanımlama
-   [x] Artırma / Azaltma
-   [x] `eger` kontrol yapısı
-   [x] Konsola `yazdir`
-   [ ] Döngüler (`dongu`)
-   [ ] Fonksiyonlar (`fonk`)
-   [ ] String tip desteği

------------------------------------------------------------------------

## 📄 Lisans

MIT License altında serbestçe kullanılabilir.
