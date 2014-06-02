#!/bin/sh

: ${JAVA_HOME:?"Need to set JAVA_HOME non-empty"}
: ${H2_HOME:?"Need to set H2_HOME non-empty"}

echo "========================================================================="
echo ""
echo "  Project Bootstrap Environment"
echo ""
echo "  JAVA_HOME: $JAVA_HOME"
echo ""
echo "  H2_HOME: $H2_HOME"
echo ""
echo "========================================================================="
RUN_CMD="env JAVA_HOME=${JAVA_HOME} ${H2_HOME}/bin/h2.sh"
eval "${RUN_CMD}"
