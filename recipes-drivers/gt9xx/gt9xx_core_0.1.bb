SUMMARY = "Goodix GT9xx touchscreen driver"
DESCRIPTION = "Bitbake recipe for Goodix GT9xx touchscreen driver"
SECTION = "kernel/modules"
PRIORITY = "optional"
LICENSE = "CLOSED"

SRCREV = "243af6a2eaa8cdb5bcc07f3e086c8dd7712f05d7"
SRC_URI = "git://github.com/rohitzsh/chipsee-driver-gt9xx.git;branch=master;protocol=https"

S="${WORKDIR}/git"

inherit module

do_compile() {
   oe_runmake 'MODPATH="${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/input/touchscreen" ' \
               'KDIR="${STAGING_KERNEL_DIR}"' \
               'KERNEL_VERSION="${KERNEL_VERSION}"'
}

do_install() {
   install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/input/touchscreen
   install -m 0644 ${S}/g*${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/input/touchscreen
}

RPROVIDES_${PN} += "kernel-module-touchscreen"
