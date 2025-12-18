#!/usr/bin/env sh

DIR="$(cd "$(dirname "$0")" && pwd)"

GRADLE_WRAPPER_JAR="$DIR/gradle/wrapper/gradle-wrapper.jar"

if [ ! -f "$GRADLE_WRAPPER_JAR" ]; then
  echo "Gradle wrapper jar not found!"
  exit 1
fi

java -classpath "$GRADLE_WRAPPER_JAR" org.gradle.wrapper.GradleWrapperMain "$@"
