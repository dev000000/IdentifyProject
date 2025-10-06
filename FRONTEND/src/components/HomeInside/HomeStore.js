// import {makeAutoObservable, runInAction} from "mobx";
// import { getMyProfile } from './HomeService';
// export default class HomeStore {
//   userDetails = {};
//   constructor() {
//     makeAutoObservable(this);
//   }
//   async getUserDetails() {
//     try {
//       let response = await getMyProfile();
//       runInAction(() => {
//         this.userDetails = response.data;
//       })
      
//     } catch (error) {
//       console.error("Error get User Details", error);
//     }
//   }
 
// }
