# FAST - Football Analytical and Statistical Tool

![FAST logo](https://raw.github.com/jsenko/PA165/master/web/src/main/webapp/img/logo.jpg)

This is a team project for our advanced Java course at Masaryk University.
It is a simple tool to keep records of football players, teams, matches and goals during a single league season.

## Modules

* __fast-api__ - contains JPA entities, data access object interfaces, service interfaces and data transfer objects
* __fast-app__ - contains an implementation of fast-api, using JPA with Eclipse Link
* __fast-web__ - is a web presentation layer for fast-app, which uses action-based Stripes framework
* __fast-cli__ - is a command line tool to access data from fast-web via REST. For details on how to use it see below.

## Building and Testing

This project uses Maven, so in order to build it, just run:
```
cd PA165; mvn install
```
The project can be easily deployed locally using embedded glassfish:
```
cd PA165; mvn verify -Dlocal
```
provided that there are jars in the target directories.
The web app will be accessible here: http://localhost:8080/pa165
After deploying locally, you can run fast-cli in interactive mode to test it out:
```
cd PA165; mvn verify -Dcli
```

## FAST Command Line Interface Tool
The tool is not interactive, however it contains interactive mode (see below).
The CLI works by interpreting a token which does not begin with `--` as a command.
The command can be then followed by its arguments in a form `--name [value]`.
The arguments can be followed by a subcommand with it's own arguments and so on.
Each command can be run with `--help` argument to display help message.

Example:
```
java -jar [...] --help
```
Displays a help message (or a one similar to):
```
FAST CLI Tool Help
Available subcommands:
 team - display, create and edit teams
 player - display, create and edit players
 interactive - run in interactive mode
Available arguments:
 --uri - (required) uri of the rest service
```
You can display table of teams, for examle, by running:
```
java -jar [...] --uri "http://localhost:8080/pa165/rest" team select
```
where select is a subcommand of team command.

### Interactive mode
Can be run by executing:
```
java -jar [...] --uri [...] interactive
```
The program then basically remembers the `--uri` of the resource
and accepts subsequent commands in a loop without the need to enter it each time.
Example:
```
$java -jar [...] --uri [...] interacive
Interactive CLI. (type 'help' for info, 'quit' to exit):
> team create --name "FC Java"
Team 'FC Java' created successfully!
> team select
+----+---------+
| id |    name |
+----+---------+
|  1 | FC Java |
+----+---------+
Total 1 row(s).
> quit
bye!
```

