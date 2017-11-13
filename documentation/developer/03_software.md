# Software

I develop under LinuxMint (Ubuntu) and Windows.
Installer creation for Windows can be done in Windows and LinuxMint, for Linux (Ubuntu) in LinuxMint only.

In order to develop the RefereeManager, you will need:

- Java 8+
- Git
- Ant (Java)

I strongly recommend using:

- eclipse
- Multimarkdown
- LaTeX

In order to create releases, one needs:

- NSIS
- dpkg


## Java 8+

The RefereeManager is written in Java 8+, using JavaFX, JAXB and language features introduced in Java 8.

## Git

You need *git* for handling the developed code.

## Ant

You need *ant* for execution of the JAXB and release scripts.
*Ant* requires *Java*.
You can choose not to use *ant*, in this case you have to execute the required scripts by hand.

## eclipse

The development of thr RefereeManager takes place in eclipse, an eclipse project and launches are provided in the repository.

## Multimarkdown

The pdf version of the documentation is created using Multimarkdown.

Additionally, many of the templates for letter, or list creation use Multimarkdown.

## LaTeX

The pdf version of the documentation is created using LaTeX.

Additionally, many of the templates for letter, or list creation use LaTeX.

## NSIS

The Windows installer is created with NSIS.

## dpkg

The Debian (Ubuntu) installer is created using dpkg.
