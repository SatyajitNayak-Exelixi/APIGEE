# 📘 Top 30 Daily-Use Linux Commands with Explanations

This cheat sheet provides a list of 30 commonly used Linux commands that are helpful for daily operations and scripting. Each command includes a short explanation to give context.

---

## 🔧 1. `ls`

**List directory contents**

```
ls -l     # Long listing format
ls -a     # Show hidden files
```

## 📂 2. `cd`

**Change directory**

```
cd /path/to/dir
cd ..     # Move up one level
```

## 📝 3. `touch`

**Create empty file(s)**

```
touch file.txt
```

## 📄 4. `cat`

**View content of files**

```
cat file.txt
```

## 📁 5. `mkdir`

**Make a new directory**

```
mkdir new_folder
```

## 🗑️ 6. `rm`

**Remove files or directories**

```
rm file.txt
rm -r folder_name
```

## ✏️ 7. `nano` / `vim`

**Edit text files in the terminal**

```
nano file.txt
vim file.txt
```

## 💾 8. `cp`

**Copy files or directories**

```
cp file1.txt file2.txt
cp -r dir1 dir2
```

## 🔁 9. `mv`

**Move or rename files**

```
mv oldname.txt newname.txt
```

## 🔍 10. `find`

**Search files and folders**

```
find . -name "file.txt"
```

## 🔎 11. `grep`

**Search for patterns in files**

```
grep 'search_term' file.txt
```

## 🔧 12. `chmod`

**Change file permissions**

```
chmod +x script.sh
```

## 🔐 13. `chown`

**Change file ownership**

```
chown user:group file.txt
```

## 🔗 14. `ln`

**Create links (soft or hard)**

```
ln -s /path/to/target link_name
```

## 📦 15. `tar`

**Archive files**

```
tar -czvf archive.tar.gz folder/
```

## 📦 16. `zip` / `unzip`

**Compress or extract files**

```
zip files.zip file1 file2
unzip files.zip
```

## 🌐 17. `wget`

**Download files from the web**

```
wget http://example.com/file.txt
```

## 🕸️ 18. `curl`

**Transfer data from or to a server**

```
curl -O http://example.com/file.txt
```

## 📊 19. `df`

**Show disk space usage**

```
df -h
```

## 📉 20. `du`

**Estimate file space usage**

```
du -sh folder/
```

## 🧠 21. `top`

**Display running processes**

```
top
```

## 🧠 22. `htop`

**Advanced process viewer (needs install)**

```
htop
```

## 👤 23. `whoami`

**Display current user**

```
whoami
```

## 👥 24. `users` / `who`

**Show who is logged in**

```
users
who
```

## 🔒 25. `sudo`

**Run command as another user (usually root)**

```
sudo apt update
```

## 🔁 26. `history`

**Show command history**

```
history
```

## 🔄 27. `ps`

**List current processes**

```
ps aux
```

## 🔌 28. `kill`

**Kill a process by PID**

```
kill 1234
```

## 🌐 29. `ping`

**Check network connectivity**

```
ping google.com
```

## 🧱 30. `ipconfig`

**Check the IP**

---

✅ Keep this list handy as a reference while working in the terminal!
