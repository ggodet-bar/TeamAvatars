TeamAvatars Readme
==================

This simple application demonstrates the [Sonata framework](http://github.com/ggodet-bar/Sonata/tree/master).


Features
---------

*	"Rich" Swing+[JNA](https://jna.dev.java.net) interface (i.e., not a form-based UI)
*	Use of MySQL for persistence
*	Several Sonata connections for handling the communications between the UI and the functional kernel


Setup
------

1.	Install the [MySQL JDBC connector](http://dev.mysql.com/downloads/connector/j/5.1.html), [AspectJ](http://eclipse.org/aspectj) and [JNA](https://jna.dev.java.net)
2.	Copy the corresponding jar files in the `lib` directory of your TeamAvatar project
3.	Copy the Sonata jar file you generated previously in the `lib` directory of your TeamAvatar project
4.	Compile with the following command: `ant compile -Daspectjpath=<path_to_your_aspectj_installation>`
5.	Run with the following command: `ant run`


So, what's relevant in this project?
-------------------------------------

[TBD]