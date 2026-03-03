import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

//创建vuex实例
export default new Vuex.Store({
   state: {
      user: null,
   },
   mutations: {
      setUser (state, user) {
         state.user = user;
      },
      updateUserStudyShort (state, { user, studyShort }) {
         if (state.user && user.name === state.user.name) { // 确保是同一个用户  
            state.user.studyshort = studyShort;
         }
      }

   },
   actions: {
      updateUserStudyShortAction ({ commit }, { user, studyShort }) {
         commit('updateUserStudyShort', { user, studyShort });
      }
   }
})