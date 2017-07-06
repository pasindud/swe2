command
keytool -genseckey -keystore aes-keystore.jck -storetype jceks -storepass "8u5+6an-QR!CagS<" -keyalg AES -keysize 256 -alias jceksaes -keypass "Ef6n+}Z;]S6hN!Qs"


Keystore Parameters
genseckey
Generate SecretKey. This is the flag indicating the creation of a synchronous key which will become our AES key
keystore
Location of the keystore. If the keystore does not exist, the tool will create a new store. Paths can be relative or absolute but must be local
storetype
this is the type of store (JCE, PK12, JCEKS, etc). JCEKS is used to store symmetric keys (AES) not contained within a certificate.
storepass
password related to the keystore. Highly recommended to create a strong passphrase for the keystore
Key Parameters
keyalg
algorithm used to create the key (AES/DES/etc)
keysize
size of the key (128, 192, 256, etc)
alias
alias given to the newly created key in which to reference when using the key
keypass
password protecting the use of the key