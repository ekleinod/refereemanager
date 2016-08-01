# Referee Manager

The referee manager is a free, open tool for managing table tennis referees and their assignments.

It is a web based application using *CakePHP* as base.

License: Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License, see file LICENSE.

## Releases

There are two ways to get releases of the referee manager:

1. via tag in *github*: https://github.com/ekleinod/refereemanager/releases
2. via download in *sourceforge*: https://sourceforge.net/projects/refereemanager/files/latest/download

## Git-Repository

The project uses *git* as means of version management.
It uses *github* for providing the git server.

The repository structure and working process is based on thoughts about a successful git branching model, described in http://nvie.com/posts/a-successful-git-branching-model/

This means, there are always these three branches:

1. `master` - contains released versions
2. `develop` - development branch, synchronizing the feature, release, and hotfix branches
3. `feature/work` - main branch in which I work/develop

Additionally the following branches may be created:

- `feature/*` - for writing a special feature
- `release/*` - releasing a new version, merging all features between `develop` and `master`
- `hotfix/*` - hotfixes for fast error correction

## ToDo

- Why is `/refereemanager/src/main/java/de/edgesoft/refereemanager/jaxb/AdditionalType.java` generated? It can be deleted without problems. Strange.

## Versions

At the moment, there is no stable, productive version available.
Several features are working, see version list below.

**Important:** the focus of development at the moment is *features*, not safety.
Therefore, the referee manager is *not* safe for using in the "real" internet.
Use it in a closed envorinment (local server etc.) only.

### v0.7.0

*Released:* 2016-08-01

- database operations: sort and remove unused clubs

### v0.6.0

*Released:* 2016-07-31

- export of contact and disposition list in mmd
- first draft of template language

### v0.5.0

*Released:* 2016-07-27

- working java backend
- export of anonymous referee list in mmd
- many improvements and changes in data modeling

### v0.4.0

*Released:* 2016-05-25

- extensive rewrite of database and functions
- giving up because of complexity of database and PHP
- switch to NoSQL data (XML) and java backend with simplified PHP frontend or static pages
- saving progress of PHP and database modeling
- converter database -> XML

### v0.3

*Released:* 2015-07-20

- many functions working for referees and messages
- last version before switching to CakePHP 3.*

### v0.2

*Released:* 2014-05-04

- database for referees working
- output of referee lists: HTML, PDF, Excel

### v0.1

*Released:* 2013-10-13

- no special functionality
- saving the code before introducing the new branching model

