#!/bin/sh

#MAVEN_BUILD=3.2.1
#JDK=8

#echo Using Maven ${MAVEN_BUILD} as build environment
#sudo update-alternatives --set mvn /home/berkar/Tools/apache-maven-${MAVEN_BUILD}/bin/mvn;
#
#sudo sudo update-java-alternatives -s java-${JDK}-sun;
#echo Setting Java${JDK} as JDK

export JAVA_HOME="/usr/lib/jvm/java-8-oracle"
echo Setting JAVA_HOME to: ${JAVA_HOME}

export JBOSS_HOME="${HOME}/Tools/jboss8"
echo Setting JBOSS_HOME to: ${JBOSS_HOME}

export H2_HOME="${HOME}/Tools/h2"
echo Setting H2_HOME to: ${H2_HOME}

export PROJ_FAMILY_RECIPES_HOME="$(pwd)"
echo Setting PROJ_FAMILY_RECIPE_HOME to: ${PROJ_FAMILY_RECIPES_HOME}

