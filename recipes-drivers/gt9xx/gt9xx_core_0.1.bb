SUMMARY = "Goodix GT9xx touchscreen driver"
DESCRIPTION = "Bitbake recipe for Goodix GT9xx touchscreen driver"
SECTION = "kernel/modules"
PRIORITY = "optional"
LICENSE = "CLOSED"

SRCREV = "af65c7070a5b1c86c4d193dc31460c10f533b305"
SRC_URI = "git://github.com/rohitzsh/chipsee-driver-gt9xx.git;branch=master;protocol=https"
## comment
## SRC_URI[sha256sum] = "c66c73ae1b74e76d6d025c313a9fdbd04e2fbd4f744c984fcf60adbfc802136b"

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
