<template>
  <div class="app-container">
    <el-row :gutter="40" v-loading="loading"
            element-loading-text="加载列表中">
      <el-col :span="6">
        <el-upload
          action=""
          class="upload-demo"
          :on-remove="handleRemove"
          :file-list="fileList"
          :http-request="handUpload"
          :on-change="handleChange"
          list-type="picture"
          :limit="5"
        >
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
      </el-col>
      <el-col :span="6" style="margin-top: 35px">
        <span class="demonstration">轮播等级</span>
        <div class="block" style="margin-top: 35px;margin-bottom: 60px" v-for="(img,index) in fileList" :key="index">
          <el-slider
            :min="1"
            :max="5"
            v-model="img.reorder"
            :step="1"
            @change="changeReorder()"
            show-stops>
          </el-slider>
        </div>
      </el-col>
    </el-row>
    <el-button type="success" style="margin-top: 20px" @click="putRotation">更新</el-button>
  </div>
</template>

<script>
  import {getRotation, postRotation, putRotation} from '@/api/student/rotation.js'

  /**
   * 找指定数组中指定对象的下标
   * @param list
   * @param id
   */
  function deleteItem(list, url) {
    for (var i = 0; i < list.length; i++) {
      if (list[i].url === url) {
        return i;
      }
    }
  }

  export default {
    created() {
      getRotation().then(res => {
        this.fileList = res.data
        this.loading = false
      })
    },
    name: "Rotation",
    data() {
      return {
        loading: true,
        fileList: []
      };
    },
    methods: {
      handleRemove(file) {
        this.fileList.splice(deleteItem(this.fileList, file.url), 1)
      },
      handUpload(file) {
        postRotation(file.file).then(res => {
          this.fileList.push({url: res.data, name: file.file.name, reorder: 0})
        })
      },
      handleChange() {

      },
      putRotation() {
        putRotation(this.fileList).then(res => {
          getRotation().then(res => {
            this.fileList = res.data
          })
        })
      }
    }
  }
</script>

<style scoped>

</style>
