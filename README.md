# sossity-version-parser
pull version information from sossity config files to prep for deploy job

this is a small utility application. it is tightly coupled with the 
configuration shell script in the controller project.  the output of this
project is a file formatted thusly:

<bucket> <full key> <name to land it on disk>

to use the sample controller project demo config, this jar will be called 
and executed.  try not to worry to much.
