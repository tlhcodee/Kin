# KinLanguage

KinLanguage, **Türkçe tabanlı basit bir yorumlayıcı (interpreter) programlama dili**dir.  
Amaç, temel programlama mantığını Türkçe sözdizimi ile öğretmek ve kolayca anlaşılır bir deneyim sunmaktır.

---

## ✨ Özellikler

-   **Değişken tanımlama** (`tam`)
-   **Koşullu ifadeler** (`eger`)
-   **Döngüler** (`dongu`)
-   **Konsola çıktı verme** (`yazdir`)
-   **Artırma / Azaltma** (`+=`, `++`, `-=`, `--`)
-   **Yorum satırları** (`#`)

---

## 🚀 Örnek Kod

```plaintext
# Bu bir yorum satırıdır, çalıştırılmaz.

tam round = 0
tam limit = 10

dongu(15) {
    round += 1

    eger(round == 5) ise
        atla
    }

    eger(round == 8) ise
        bitir
    }

    yazdir("Round: " + round)

    eger(round > limit) ise {
        yazdir("Limit aşıldı! -> " + round)
    }
}
```

### 📌 Beklenen Çıktı

```plaintext
Round: 1
Round: 2
Round: 3
Round: 4
Round: 6
Round: 7
```

(`round == 5` → `atla` çalıştı, o iterasyon atlandı.  
 `round == 8` → `bitir` çalıştı, döngü kırıldı.)

---

## 🔑 Sözdizimi Açıklamaları

### 1. Değişkenler (`tam`)

Bir tam sayı değişkeni tanımlamak için `tam` kullanılır:

```plaintext
tam skor = 10
```

---

### 2. Koşullar (`eger`)

Koşullu ifadeler için:

```plaintext
eger(skor > 5) {
    yazdir("Yeterli skor!")
}
```

Artık **önceden tanımlanmış değişken olmasa da** direkt kullanılabilir:

```plaintext
eger(5 < 10) {
    yazdir("Koşul doğru!")
}
```

---

### 3. Döngüler (`dongu`)

Belirtilen tekrar sayısı kadar kod çalıştırır:

```plaintext
dongu(3) {
    yazdir("Merhaba")
}
```

Çıktı:

```plaintext
Merhaba
Merhaba
Merhaba
```

#### 🔄 Özel Anahtarlar
- `atla` → `continue` (o turu atla, sonraki tura geç)  
- `bitir` → `break` (döngüyü tamamen kır)

---

### 4. Yazdırma (`yazdir`)

Konsola çıktı vermek için:

```plaintext
yazdir("Merhaba Dünya")
```

Değişkenlerle birleştirme yapılabilir:

```plaintext
tam adim = 5
yazdir("Adım sayısı: " + adim)
```

Çıktı:

```plaintext
Adım sayısı: 5
```

---

### 5. Yorum Satırları (`#`)

`#` ile başlayan satırlar çalıştırılmaz:

```plaintext
# Bu satır yorumdur
tam x = 5 # satırın sonunda da kullanılabilir
```

---

## 🛠 Geliştirme Durumu

-   [x] Değişken tanımlama
-   [x] Artırma / Azaltma
-   [x] `eger` kontrol yapısı
-   [x] Konsola `yazdir`
-   [x] Döngüler (`dongu`)
-   [x] Yorum satırları
-   [ ] Fonksiyonlar (`fonk`)
-   [ ] String tip desteği

---

## 📢 Güncellemeler

### **Update 0.2 – Döngüler ve Daha Fazlası**
- Döngü sistemi eklendi → `dongu(tekrar)`
- `IF` artık önceden oluşturulmuş değişken gerektirmiyor (`eger(5 < 10)`)
- `atla` (continue) ve `bitir` (break) desteği eklendi
- Yorum satırları (`#`) eklendi
- Kod tabanı iyileştirildi, daha modern ve temiz hale getirildi

---

## 📄 Lisans
MIT License altında serbestçe kullanılabilir.
