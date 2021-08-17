#!/bin/bash

git checkout "${CI_COMMIT_BRANCH}"

awk -F= '
{ val = $2 }
$1 == "versionCode" { val++ }
{ print $1 "=" val }
' version.properties > version.properties.temp
mv version.properties.temp version.properties

read versionName <<< $(awk -F= '$1 == "versionName" { print $2 }' version.properties)
read versionCode <<< $(awk -F= '$1 == "versionCode" { print $2 }' version.properties)

git add version.properties
git commit -m "v$versionName ($versionCode) [build-tc] [skip ci]"
git remote add origin_ci git@git.coral.club:mobile/coral-health-android.git
git push origin_ci "${CI_COMMIT_BRANCH}"
