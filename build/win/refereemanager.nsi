# refereemanager install script.

## Legal stuff
#
# Copyright 2016-2017 Ekkart Kleinod <ekleinod@edgesoft.de>
#
# This file is part of refereemanager.
#
# refereemanager is free software: you can redistribute it and/or modify
# it under the terms of the GNU Lesser General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
#
# refereemanager is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU Lesser General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public License
# along with refereemanager.  If not, see <http://www.gnu.org/licenses/>.
#
# @author Ekkart Kleinod
# @version 0.14.0
# @since 0.14.0

# use encoding: ISO-8859-15

!define JAR_NAME Refereemanager
!define REGKEY "Software\Refereemanager"
!define VERSION **version**
!define LONG_VERSION "**longversion**"
!define COMPANY "Ekkart Kleinod (edge-soft)"
!define URL http://www.edgesoft.de/
!define LONGNAME "refman - Der Referee-Manager"
!define FILENAME "refereemanager"
!define DIRNAME ${FILENAME}
!define INSTALLNAME "..\..\files\${FILENAME}-${LONG_VERSION}.exe"

!define RESOURCE_DIR "..\..\refereemanager\src\main\resources\"

!include ..\..\submodules\edgeutils\nsis\simple-jar.nsi

# EOF
