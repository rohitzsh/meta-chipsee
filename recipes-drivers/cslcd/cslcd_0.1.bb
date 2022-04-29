SUMMARY = "Chipsee LCD drivers"
DESCRIPTION = "Bitbake recipe for chipsee LCD drivers"
SECTION = "kernel/modules"
PRIORITY = "optional"
LICENSE = "CLOSED"

SRCREV = "09a1f2551b7c50b03551b055c034ac69db93e6ac"
SRC_URI = "git://github.com/rohitzsh/chipsee-driver-lcd.git;branch=master;protocol=https"
SRC_URI[sha256sum] = "c66c73ae1b74e76d6d025c313a9fdbd04e2fbd4f744c984fcf60adbfc802136b"

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
