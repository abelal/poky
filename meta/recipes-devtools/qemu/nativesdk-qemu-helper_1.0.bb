SUMMARY = "Qemu helper scripts"
LICENSE = "GPLv2"
RDEPENDS_${PN} = "nativesdk-qemu nativesdk-python3-subprocess \
                  nativesdk-python3-shell nativesdk-python3-fcntl \
                "

PR = "r9"

LIC_FILES_CHKSUM = "file://${WORKDIR}/tunctl.c;endline=4;md5=ff3a09996bc5fff6bc5d4e0b4c28f999 \
                    file://${COREBASE}/scripts/runqemu;endline=19;md5=a8ad1905c709a2deaa057608ebaf705a"


SRC_URI = "file://${COREBASE}/scripts/runqemu \
           file://${COREBASE}/scripts/runqemu-addptable2image \
           file://${COREBASE}/scripts/runqemu-gen-tapdevs \
           file://${COREBASE}/scripts/runqemu-ifup \
           file://${COREBASE}/scripts/runqemu-ifdown \
           file://${COREBASE}/scripts/oe-find-native-sysroot \
           file://${COREBASE}/scripts/runqemu-extract-sdk \
           file://${COREBASE}/scripts/runqemu-export-rootfs \
           file://tunctl.c \
           file://raw2flash.c \
          "

S = "${WORKDIR}"

inherit nativesdk

do_compile() {
	${CC} tunctl.c -o tunctl
	${CC} raw2flash.c -o raw2flash.spitz
	${CC} raw2flash.c -o flash2raw.spitz -Dflash2raw
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}${COREBASE}/scripts/oe-* ${D}${bindir}/
	install -m 0755 ${WORKDIR}${COREBASE}/scripts/runqemu* ${D}${bindir}/
	install tunctl ${D}${bindir}/
	install raw2flash.spitz ${D}${bindir}/
	install flash2raw.spitz ${D}${bindir}/
	ln -fs raw2flash.spitz ${D}${bindir}/raw2flash.akita
	ln -fs raw2flash.spitz ${D}${bindir}/raw2flash.borzoi
	ln -fs raw2flash.spitz ${D}${bindir}/raw2flash.terrier
	ln -fs flash2raw.spitz ${D}${bindir}/flash2raw.akita
	ln -fs flash2raw.spitz ${D}${bindir}/flash2raw.borzoi
	ln -fs flash2raw.spitz ${D}${bindir}/flash2raw.terrier
}
