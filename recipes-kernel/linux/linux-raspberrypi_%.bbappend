SRC_URI += "file://cs-i2c0-overlay.dts;subdir=git/arch/${ARCH}/boot/dts"
SRC_URI += "file://gt9xx-overlay.dts;subdir=git/arch/${ARCH}/boot/dts"
SRC_URI += "file://pwm-backlight-overlay.dts;subdir=git/arch/${ARCH}/boot/dts"
SRC_URI += "file://seeed-2mic-voicecard-overlay.dts;subdir=git/arch/${ARCH}/boot/dts"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
