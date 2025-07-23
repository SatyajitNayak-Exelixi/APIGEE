# 🧰 Git & GitHub.

## 🧠 What is Git?

Git is a **distributed version control system** used to track changes in source code during software development. It allows multiple developers to work on the same project, collaborate efficiently, and revert changes if something goes wrong.

### 🔑 Key Features:

* Tracks history of changes to code
* Supports branching and merging
* Enables collaboration between teams
* Works offline (local repo)

## 🌍 What is GitHub?

GitHub is a **web-based hosting service** for Git repositories. It provides a user-friendly interface, collaboration features like pull requests, issue tracking, code reviews, and integration with CI/CD tools.

### 🔑 Key Features:

* Cloud hosting for Git repositories
* Collaboration with teams using pull requests
* Built-in issue tracking and project boards
* GitHub Actions for automation
* Security and access controls

---

## 🔧 1. `git init`

**Initialize a new Git repository in the current directory**

```
git init
```

## 🧬 2. `git clone`

**Clone an existing repository from GitHub or another remote**

```
git clone https://github.com/user/repo.git
```

## 📦 3. `git status`

**Show current changes and staging status**

```
git status
```

## 📝 4. `git add`

**Stage files for commit**

```
git add file.txt
git add .      # Add all changes
```

## 💾 5. `git commit`

**Commit staged changes with a message**

```
git commit -m "Your commit message"
```

## 🔁 6. `git push`

**Push commits to a remote repository**

```
git push origin main
```

## 🔄 7. `git pull`

**Fetch and merge changes from the remote repo**

```
git pull origin main
```

## 🌿 8. `git branch`

**List, create, or delete branches**

```
git branch           # List branches
git branch new-feature  # Create new branch
```

## 🔀 9. `git checkout`

**Switch between branches or restore files**

```
git checkout main
```

## 🔀 10. `git switch`

**Modern way to switch branches**

```
git switch feature-branch
```

## 🧪 11. `git merge`

**Merge changes from one branch into another**

```
git merge feature-branch
```

## 💣 12. `git reset`

**Unstage files or move HEAD**

```
git reset file.txt      # Unstage file
```

## 🧹 13. `git clean`

**Remove untracked files**

```
git clean -fd
```

## 📚 14. `git log`

**View commit history**

```
git log --oneline
```

## 🔍 15. `git diff`

**See changes between commits, branches, or working tree**

```
git diff
```

## 🖍️ 16. `git config`

**Set user info and preferences**

```
git config --global user.name "Your Name"
git config --global user.email "you@example.com"
```

## 🔗 17. `git remote`

**Manage connections to remote repositories**

```
git remote -v
```

## 🗑️ 18. `git rm`

**Remove files from working directory and staging**

```
git rm file.txt
```

## ✏️ 19. `git mv`

**Move or rename files**

```
git mv old.txt new.txt
```

## 🧾 20. `git stash`

**Temporarily save changes without committing**

```
git stash
```

## 📥 21. `git fetch`

**Get changes from remote without merging**

```
git fetch origin
```

## 🏷️ 22. `git tag`

**Create tags for marking releases**

```
git tag v1.0
```

## 🔄 23. `git rebase`

**Reapply commits on top of another base tip**

```
git rebase main
```

## 🧠 24. `git blame`

**Show who changed each line of a file**

```
git blame file.txt
```

## 🧪 25. `git show`

**Show details of a commit or object**

```
git show commit_id
```

## 🚨 26. `git revert`

**Create a new commit that undoes a previous commit**

```
git revert commit_id
```

## 🔐 27. `ssh-keygen`

**Generate SSH keys for GitHub authentication**

```
ssh-keygen -t rsa -b 4096 -C "your_email@example.com"
```

## 🧾 28. `git cherry-pick`

**Apply a specific commit from another branch**

```
git cherry-pick commit_id
```

## 🚧 29. `git reflog`

**Show history of HEAD movements**

```
git reflog
```

## 🚿 30. `git bisect`

**Find the commit that introduced a bug**

```
git bisect start
```

---

✅ Use this list to navigate daily Git workflows, manage your codebase, and contribute confidently to GitHub projects.
