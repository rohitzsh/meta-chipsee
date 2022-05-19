SUMMARY = "STMicroelectronics lsm6ds3 driver"
DESCRIPTION = "Bitbake recipe for STMicroelectronics lsm6ds3 driver"
SECTION = "kernel/modules"
PRIORITY = "optional"
LICENSE = "CLOSED"

SRCREV = "269f07ccda39f38b79518d6e33ea615885bc46e9"
SRC_URI = "git://github.com/rohitzsh/chipsee-driver-lsm6ds3.git;branch=master;protocol=https"

S="${WORKDIR}/git"

inherit module

do_compile() {
   oe_runmake 'MODPATH="${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/input/misc" ' \
               'KDIR="${STAGING_KERNEL_DIR}"' \
               'KERNEL_VERSION="${KERNEL_VERSION}"'
}

do_install() {
   install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/input/misc
   install -m 0644 ${S}/l*${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/input/misc
}

RPROVIDES_${PN} += "kernel-module-lsm6ds3"
