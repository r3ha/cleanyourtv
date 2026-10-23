#!/usr/bin/env bash
# 一键恢复脚本：恢复所有被停用的应用
set -e
TV_HOST=192.168.31.189
PY=/Users/r3ha/Projects/CleanYourTV/tools/venv/bin/python
TV_PY=/Users/r3ha/Projects/CleanYourTV/tools/tv.py

pkgs=(
  com.miui.analytics
  com.miui.tv.analytics
  com.miui.misightservice
  com.xiaomi.tvqs
  com.mitv.cloudcontrol
  com.duokan.videodaily
  com.mitv.shoplugin
  com.xiaomi.mitv.shop
  com.xm.webcontent
  com.xiaomi.aitranslate
  com.xiaomi.mitv.health
  com.xiaomi.gamecenter.sdk.service.mibox
)

echo '正在恢复应用...'
for pkg in "${pkgs[@]}"; do
  TV_HOST=$TV_HOST $PY $TV_PY "pm enable $pkg" || true
done
echo '恢复完成！'
