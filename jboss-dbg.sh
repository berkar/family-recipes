#!/bin/sh

: ${JAVA_HOME:?"Need to set JAVA_HOME non-empty"}
: ${JBOSS_HOME:?"Need to set JBOSS_HOME non-empty"}
: ${PROJ_FAMILY_RECIPES_HOME:?"Need to set PROJ_FAMILY_RECIPES_HOME non-empty"}

# Copy all required configuration files
JBOSS_SRCDIR="${PROJ_FAMILY_RECIPES_HOME}/installer/src/main/resources/jboss"
for file in $(ls ${JBOSS_SRCDIR}/bin);
do
    cp "${JBOSS_SRCDIR}/bin/$file" "${JBOSS_HOME}/bin/."
done
for file in $(ls ${JBOSS_SRCDIR}/standalone/configuration);
do
    cp "${JBOSS_SRCDIR}/standalone/configuration/$file" "${JBOSS_HOME}/standalone/configuration/."
done

RUN_XML="standalone-recipes-dbg.xml"
RUN_CONF="${JBOSS_HOME}/bin/standalone-recipes-dbg.conf"
RUN_PROPERTIES="${JBOSS_HOME}/standalone/configuration/recipes_utv.properties"

echo "========================================================================="
echo ""
echo "  Project Bootstrap Environment"
echo ""
echo "  JAVA_HOME: $JAVA_HOME"
echo ""
echo "  JBOSS_HOME: $JBOSS_HOME"
echo ""
echo "  RUN_CONF: $RUN_CONF"
echo ""
echo "  RUN_XML: $RUN_XML"
echo ""
echo "  RUN_PROPERTIES: $RUN_PROPERTIES"
echo ""
echo "  PROJ_FAMILY_RECIPES_HOME: $PROJ_FAMILY_RECIPES_HOME"
echo ""
echo "========================================================================="
RUN_CMD="env JAVA_HOME=${JAVA_HOME} JBOSS_HOME=${JBOSS_HOME} RUN_CONF=${RUN_CONF} ${JBOSS_HOME}/bin/standalone.sh --server-config=${RUN_XML} -P ${RUN_PROPERTIES}"
eval "$RUN_CMD"
