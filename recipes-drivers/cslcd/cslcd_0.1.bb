SUMMARY = "Chipsee LCD drivers"
DESCRIPTION = "Bitbake recipe for chipsee LCD drivers"
SECTION = "kernel/modules"
PRIORITY = "optional"
LICENSE = "CLOSED"

SRCREV = "de1a87832bc700aeaade186d4692a7e57eb02b62"
SRC_URI = "git://github.com/rohitzsh/chipsee-driver-lcd.git;branch=master;protocol=https"

S="${WORKDIR}/git"

inherit module

do_compile() {
   oe_runmake 'MODPATH="${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/cs_lcd" ' \
               'KDIR="${STAGING_KERNEL_DIR}"' \
               'KERNEL_VERSION="${KERNEL_VERSION}"'
}

do_install() {
   install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/cs_lcd
   install -m 0644 ${S}/c*${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/cs_lcd
}

RPROVIDES_${PN} += "kernel-module-cs_lcd"
