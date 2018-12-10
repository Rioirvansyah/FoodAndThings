<?php

require APPPATH .'/libraries/REST_Controller.php';

class Wilayah extends REST_Controller {

	// show data pembelian
   function index_get() {
       $get_wilayah = $this->db->query("SELECT wilayah.id_wilayah, wilayah.nama_wilayah, wilayah.kota FROM wilayah")->result();
     
       $this->response(array("status"=>"success","result" => $get_wilayah));
   }

   //Menginputkan data pada pembelian
   function index_post() {
       $data_wilayah = array(
           'id_wilayah'   => $this->post('id_wilayah'),
           'nama_wilayah' => $this->post('nama_wilayah'),
           'kota'         => $this->post('kota')
           );
      	//jika pada id_wilayah tidak diisi maka tampilkan status
       if  (empty($data_wilayah['id_wilayah'])){
            $this->response(array('status'=>'fail',"message"=>"id wilayah tidak boleh kosong"));
       }
       else {
           $getID = $this->db->query("Select id_wilayah from wilayah where id_wilayah='".$data_wilayah['id_wilayah']."'")->result();
          
           //jika id_wilayah tidak ada dalam database maka eksekusi insert
           if (empty($getID)){
                    if (empty($data_wilayah['id_wilayah'])){
                       $this->response(array('status'=>'fail',"message"=>"id wilayah tidak boleh kosong"));
                    }
                    else if(empty($data_wilayah['nama_wilayah'])){
                       $this->response(array('status'=>'fail',"message"=>"nama wilayah tidak boleh kosong"));
                    }else if(empty($data_wilayah['kota'])){
                       $this->response(array('status'=>'fail',"message"=>"kota tidak boleh kosong"));
                    }else{
                       $getIDWilayah = $this->db->query("Select id_wilayah from wilayah Where id_wilayah='".$data_wilayah['id_wilayah']."'")->result();
                       $message="";
                       if (empty($getIDWilayah)) $message.="id wilayah tidak sesuai ";
                       //jika tidak ada pesan error
                       if (empty($message)){
                           $insert= $this->db->insert('wilayah',$data_wilayah);
                           if ($insert){
                               $this->response(array('status'=>'success','result' => $data_wilayah,"message"=>$insert));   
                           }
                          
                       }else{
                           $this->response(array('status'=>'fail',"message"=>$message));   
                       }
                      
                    }
           }else{
               $this->response(array('status'=>'fail',"message"=>"id wilayah sudah ada, mohon ganti id lain"));
           }  
       }
   }


   // edit data wilayah
   function index_put() {
       $data_wilayah = array(
           'id_wilayah'   => $this->post('id_wilayah'),
           'nama_wilayah' => $this->post('nama_wilayah'),
           'kota'         => $this->post('kota')
      );
       if  (empty($data_wilayah['id_wilayah'])){
            $this->response(array('status'=>'fail',"message"=>"id_wilayah tidak boleh kosong"));
       }
       else {
           $getID = $this->db->query("Select id_wilayah from wilayah where id_wilayah='".$data_wilayah['id_wilayah']."'")->result();
          
           //jika id_wilayah tidak ada dalam database maka eksekusi insert
           if (empty($getID)){
             $this->response(array('status'=>'fail',"message"=>"id_wilayah tidak sesuai, isi dengan benar")); 
           }
            
           else {
            if (empty($data_wilayah['id_wilayah'])){
                       $this->response(array('status'=>'fail',"message"=>"id wilayah tidak boleh kosong"));
                    }
                    else if(empty($data_wilayah['nama_wilayah'])){
                       $this->response(array('status'=>'fail',"message"=>"menu wilayah tidak boleh kosong"));
                    }
                    else if(empty($data_wilayah['kota'])){
                       $this->response(array('status'=>'fail',"message"=>"harga wilayah tidak boleh kosong"));
                    }
                    else{
                      $getIDWilayah = $this->db->query("Select id_wilayah from wilayah Where id_wilayah='".$data_wilayah['id_wilayah']."'")->result();
                      $message="";
                      if (empty($getIDWilayah)) $message.="id wilayah tidak sesuai ";
                        if (empty($message)){
                          $this->db->where('id_wilayah',$data_wilayah['id_wilayah']);
                          $update= $this->db->update('wilayah',$data_wilayah);
                          if ($update){
                            $this->response(array('status'=>'success','result' => $data_wilayah,"message"=>$update));
                          }
                        }
                        else{
                          $this->response(array('status'=>'fail',"message"=>$message));   
                        }
                    }
           	  }
       	}
   }
   // delete wilayah
   function index_delete() {
       $id_wilayah = $this->delete('id_wilayah');
       if (empty($id_wilayah)){
           $this->response(array('status' => 'fail', "message"=>"id wilayah harus diisi"));
       } else {
           $this->db->where('id_wilayah', $id_wilayah);
           $delete = $this->db->delete('wilayah');  
           if ($this->db->affected_rows()) {
               $this->response(array('status' => 'success','message' =>"Berhasil hapus dengan id_wilayah = ".$id_wilayah));
           } else {
               $this->response(array('status' => 'fail', 'message' =>"id wilayah tidak dalam database"));
           }
       }
   }
}

/* End of file wilayah.php */
/* Location: ./application/controllers/wilayah.php */