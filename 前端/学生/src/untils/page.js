// export default  function onRefresh() {
//     this.$router.go(0)
// }
export   function onRe() {
    this.$router.go(0)
}

/**
 *
 *
 * @param website 要跳去的的网址
 * @param it 传入本对象
 */
export function toLocation(website,it) {
    it.$router.push(website)

}


