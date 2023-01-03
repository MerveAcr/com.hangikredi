## Local Test Env Setup
- /com.interview/src/test/resources un altindaki testing.xml sayfasindan point edilmis testleri calistirabiliriz.
- yeni suite, package, class, ve method lari point ederek istedigimiz testleri calistirabiliriz.
- yeni suite ler olustururak smoke, regression, end to end gibi testlerimizi calistirabiliriz.
- suite tagg inin icersinde paralel ve thread-count keywrod larini kullanrak paralel execution yapabiliriz.
- thread-count un degeri kac tane webdriver initalize edilicegini gosterir.

## Test Execution
- /com.interview/src/test/resources klasorune git
- testing.xml dosyasini ac
- Sag tikla
- Run As e tikla
- TestNGSuite e tikla 

- testing.xml de point edilen test lerin hepsi calisicaktir.

##  POM Design Pattern
- page object class larini com.interview.pages in altinda olusturabiliriz
- her bir safya/modul icin ayri bir page object olusturmak gerekir
- page object class larini page factory design pattern ile develop edersek @FindBy annotation i kullanarak kolayca WebElement lerimizi locate ederbiliriz
- test lerimizi com.interview.test package inin altina yazabiliriz
- page object veya test file larimizda kullanicagimiz yardimci methodlari com.interview.utilities package inin altinda olusturabilriz
- istenilen yeni library/ dependency leri pom.xml sayfasina eklemyi unutmayiniz 

## Test Report 
##### Html Report
	- project in uzerine sag tikliyarak refresh ediniz
	- proje yi yeniledikten sonra main directory de test-output klasorunun geldigini goruceksiniz. 
	- index.html dosyasini tarayicida acarak test sonuclarini goruntuluyebilirsiniz.
	- /src directory de fail olan test lerin screenshot larini test ismi ile gorebilirsiniz 

## .gitignore
- localinizde kalmasini istediginiz, jar,test result gibi dosylari .gitignore file ina ekliyerek remote repo nuzu gereksiz dosyalardan koruyabilirisiniz



