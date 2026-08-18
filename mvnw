#!/bin/sh
set -e
DIR="$(cd "$(dirname "$0")" && pwd)"
mkdir -p "$DIR/.mvn/wrapper"
if [ ! -f "$DIR/.mvn/wrapper/maven-wrapper.jar" ]; then
  echo "Downloading maven-wrapper.jar..."
  (cd "$DIR/.mvn/wrapper" && curl -s -o maven-wrapper.jar https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar) || true
fi
exec java -cp "$DIR/.mvn/wrapper/maven-wrapper.jar" "-Dmaven.multiModuleProjectDirectory=$DIR" org.apache.maven.wrapper.MavenWrapperMain "$@"