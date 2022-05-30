SUMMARY = "PWM based backlight control"
DESCRIPTION = "Bitbake recipe for PWM based backlight control"
SECTION = "kernel/modules"
PRIORITY = "optional"
LICENSE = "CLOSED"

SRCREV = "0e239e93cb436da772ba9bf1f018f65cdd56d674"
SRC_URI = "git://github.com/rohitzsh/chipsee-driver-pwmbl.git;branch=master;protocol=https"

S="${WORKDIR}/git"

inherit module

do_compile() {
   oe_runmake 'MODPATH="${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/video/backlight" ' \
               'KDIR="${STAGING_KERNEL_DIR}"' \
               'KERNEL_VERSION="${KERNEL_VERSION}"'
}

do_install() {
   install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/video/backlight
   install -m 0644 ${S}/p*${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/video/backlight
}

RPROVIDES_${PN} += "kernel-module-backlight"

KERNEL_MODULE_AUTOLOAD += "pwm_bl"
