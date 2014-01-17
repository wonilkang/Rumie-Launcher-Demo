The Rumie Initiative

The Rumie launcher is intended to replace the default Android launcher on tablets used for education in underprivileged communities.  The tablets are designed to be used in areas without regular internet access.

The goals of the Rumie launcher at this stage are:
(i) Lock the child into only using the preloaded applications on the device
(ii) Include a games section for which playing time is unlocked via codes given for good behaviour
(iii) store usage data and synchronize when connected


Quick Start

The AVD (Android Virtual Device) file for the Rumie tablet will be available here soon. 

Clone or fork the Rumie Launcher code from GitHub.


Contributing

Bugs to Fix:
1.  Launcher takes extremely long to load upon boot up - sometimes 20 seconds.  We need to optimize this.
2.  Applications sometimes disappear from the launcher upon second boot up - only way to fix is by manually deleting the cache of the launcher application.  The first step is to find out how to consistently replicate this behaviour, then fix it.

New Features:

1.  Add folders in the Learn and Play section, to better categorize types of applications.
2.  Create a way for the teacher to update the list of “approved” apps that show up in the launcher (currently there’s no way if a teacher wants to add an app aside from rewriting the code).


Copyright and license

Copyright 2013-2014 Rumie, Inc. under the MIT license.
