#!/bin/sh

: ${JAVA_HOME:?"Need to set JAVA_HOME non-empty"}
: ${JBOSS_HOME:?"Need to set JBOSS_HOME non-empty"}
: ${PROJ_HOME:?"Need to set PROJ_HOME non-empty"}

RUN_XML="standalone-recipes.xml"
JBOSS_SRCDIR="${PROJ_FAMILY_RECIPES_HOME}/installer/src/main/resources/jboss"
RUN_CONF="${JBOSS_SRCDIR}/bin/standalones-recipe.conf"
RUN_PROPERTIES="${JBOSS_SRCDIR}/standalone/configuration/recipes_utv.properties"

# Copy all required configuration files
for file in $(ls ${JBOSS_SRCDIR}/standalone/configuration);
do
    cp "${JBOSS_SRCDIR}/standalone/configuration/$file" "${JBOSS_HOME}/standalone/configuration/."
done

eval "env JAVA_HOME=${JAVA_HOME} JBOSS_HOME=${JBOSS_HOME} RUN_CONF=${RUN_CONF} ${JBOSS_HOME}/bin/standalone.sh --server-config=${RUN_XML} -P ${RUN_PROPERTIES}"
