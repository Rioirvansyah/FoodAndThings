<?php

require APPPATH .'/libraries/REST_Controller.php';

class Kategori extends REST_Controller {

	// show data pembelian
   function index_get() {
       $get_kategori = $this->db->query("SELECT kategori.id_kategori, kategori.nama_kategori FROM kategori")->result();
     
       $this->response(array("status"=>"success","result" => $get_kategori));
   }

   //Menginputkan data pada pembelian
   function index_post() {
       $data_kategori = array(
           'id_kategori'   => $this->post('id_kategori'),
           'nama_kategori'     => $this->post('nama_kategori')
           );
      	//jika pada id_kategori tidak diisi maka tampilkan status
       if  (empty($data_kategori['id_kategori'])){
            $this->response(array('status'=>'fail',"message"=>"id kategori tidak boleh kosong"));
       }
       else {
           $getID = $this->db->query("Select id_kategori from kategori where id_kategori='".$data_kategori['id_kategori']."'")->result();
          
           //jika id_kategori tidak ada dalam database maka eksekusi insert
           if (empty($getID)){
                    if (empty($data_kategori['id_kategori'])){
                       $this->response(array('status'=>'fail',"message"=>"id kategori tidak boleh kosong"));
                    }
                    //jika salah satu kolom tidak diisi
                    else if(empty($data_kategori['nama_kategori'])){
                       $this->response(array('status'=>'fail',"message"=>"menu kategori tidak boleh kosong"));
                    }else{
                       $getIDKategori = $this->db->query("Select id_kategori from kategori Where id_kategori='".$data_kategori['id_kategori']."'")->result();
                       $message="";
                       if (empty($getIDKategori)) $message.="id kategori tidak sesuai ";
                       //jika tidak ada pesan error
                       if (empty($message)){
                           $insert= $this->db->insert('kategori',$data_kategori);
                           if ($insert){
                               $this->response(array('status'=>'success','result' => $data_kategori,"message"=>$insert));   
                           }
                          
                       }else{
                           $this->response(array('status'=>'fail',"message"=>$message));   
                       }
                      
                    }
           }else{
               $this->response(array('status'=>'fail',"message"=>"id kategori sudah ada, mohon ganti id lain"));
           }  
       }
   }


   // edit data kategori
   function index_put() {
       $data_kategori = array(
           'id_kategori'   => $this->put('id_kategori'),
           'nama_kategori'     => $this->put('nama_kategori')
      );
       if  (empty($data_kategori['id_kategori'])){
            $this->response(array('status'=>'fail',"message"=>"id_kategori tidak boleh kosong"));
       }
       else {
           $getID = $this->db->query("Select id_kategori from kategori where id_kategori='".$data_kategori['id_kategori']."'")->result();
          
           //jika id_kategori tidak ada dalam database maka eksekusi insert
           if (empty($getID)){
             $this->response(array('status'=>'fail',"message"=>"id_kategori tidak sesuai, isi dengan benar")); 
           }
            
           else {
            if (empty($data_kategori['id_kategori'])){
                       $this->response(array('status'=>'fail',"message"=>"id kategori tidak boleh kosong"));
                    }
                    else if(empty($data_kategori['nama_kategori'])){
                       $this->response(array('status'=>'fail',"message"=>"menu kategori tidak boleh kosong"));
                    }
                    else{
                      $getIDKategori = $this->db->query("Select id_kategori from kategori Where id_kategori='".$data_kategori['id_kategori']."'")->result();
                      $message="";
                      if (empty($getIDKategori)) $message.="id kategori tidak sesuai ";
                        if (empty($message)){
                          $this->db->where('id_kategori',$data_kategori['id_kategori']);
                          $update= $this->db->update('kategori',$data_kategori);
                          if ($update){
                            $this->response(array('status'=>'success','result' => $data_kategori,"message"=>$update));
                          }
                      
                        }
                        else{
                          $this->response(array('status'=>'fail',"message"=>$message));   
                        }
                    }
           	  }
       	}
   }
   // delete kategori
   function index_delete() {
       $id_kategori = $this->delete('id_kategori');
       if (empty($id_kategori)){
           $this->response(array('status' => 'fail', "message"=>"id kategori harus diisi"));
       } else {
           $this->db->where('id_kategori', $id_kategori);
           $delete = $this->db->delete('kategori');  
           if ($this->db->affected_rows()) {
               $this->response(array('status' => 'success','message' =>"Berhasil hapus dengan id_kategori = ".$id_kategori));
           } else {
               $this->response(array('status' => 'fail', 'message' =>"id kategori tidak dalam database"));
           }
       }
   }
}

/* End of file kategori.php */
/* Location: ./application/controllers/kategori.php */