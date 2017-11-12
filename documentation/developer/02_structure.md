# Structure and Git repositories

The RefereeManager is written in Java 8+, using FXML and Java features introduced in Java 8.

## Structure

The repository is structured as follows:

| folder | content |
|:--|:--|
| `build` | Build files for releases. |
| `documentation` | User and developer documentation. |
| `files` | Released files (will change in the future). |
| `refereemanager` | Eclipse project with sources, ressources and tests. |
| `submodules` | Needed submodules (edgeutils for Ant and JAXB commons). |


## Git-Repository/github

The git repository is maintained using github at:

- <https://github.com/tt-schiri/refereemanager/>

Github is used for code maintenance and issue tracking.

Short information about the structure of the git repository:

The branches are constructed regarding the git branching model of http://nvie.com/posts/a-successful-git-branching-model/

This means, there are always at least three branches:

1. `master` - contains released versions
2. `develop` - main synchronisation branch for feature, release, and hotfix branches
3. `feature/work` - main working branch for development

Additionally, the following branches may occur:

- `feature/*` - writing a special feature
- `release/*` - synchronizing release versions between `develop` and `master`
- `hotfix/*` - fast bugfixes
