# Certificate Renewal Process (Exelixi)

This document explains the step-by-step process for renewing an SSL certificate, generating required files, and validating certificate expiry.

---

## Step 1: Generate Private Key and CSR

Generate a new RSA 2048-bit private key and CSR (Certificate Signing Request).

```bash
openssl req -new -newkey rsa:2048 -nodes \
-out mi_mercury_exelixi_com.csr \
-keyout mi_mercury_exelixi_com.key \
-subj "/C=US/ST=Florida/L=Miami/O=Exelixi INC/CN=mi.mercury.exelixi.com"
```

After generating the CSR, share the `.csr` file with the CA team for signing.

---

## Step 2: Receive Signed Certificate from CA

Once the CA team provides the signed certificate (`.cer` file) with the updated expiration date, proceed with conversion steps below.

Example certificate file:

```
mi.mercury.exelixi.com.cer
```

---

## Step 3: Convert Certificate to PFX Format

Create a `.pfx` (PKCS12) file using the signed certificate and private key.

```bash
openssl pkcs12 -export \
-in /app/share/archive/media/ESD_CERTS2025/MI_CERT/mi.mercury.exelixi.com.cer \
-inkey /app/share/archive/media/ESD_CERTS2025/MI_CERT/mi_mercury_exelixi_com.key \
-name rui \
-passout pass:Exelixi \
-out mi_mercury_exelixi_com.pfx
```

---

## Step 4: Create JKS from Signed Certificate (.cer)

Create a Java Keystore (JKS) directly from the signed certificate.

```bash
keytool -import -alias rui \
-file /app/share/archive/media/ESD_CERTS2025/MI_CERT/mi.mercury.exelixi.com.cer \
-keystore mi_mercury_exelixi_com.jks \
-storepass Exelixi
```

If prompted with "Trust this certificate?", type:

```
yes
```

---

## Step 5: Verify Certificate Expiry (PFX)

### Extract Certificate from PFX

```bash
openssl pkcs12 -in mi_mercury_exelixi_com.pfx -nokeys -out test.pem
```

### Check Expiry Details

```bash
openssl x509 -in test.pem -text
```

Look for:

```
Not Before:
Not After :
```

---

## Summary Flow

1. Generate CSR + Private Key
2. Share CSR with CA
3. Receive signed certificate
4. Convert to PFX
5. Convert PFX to JKS
6. Verify expiry date

---

## Important Notes

* Always keep the private key secure.
* Ensure correct alias name while creating PFX/JKS.
* Verify expiry before deploying certificate to production.
* Backup old certificate before replacement.

---

**End of Document**
