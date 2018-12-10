<?php

require APPPATH .'/libraries/REST_Controller.php';

class Makanan extends REST_Controller {

	// show data pembelian
   function index_get() {
       $get_makanan = $this->db->query("SELECT makanan.id_makanan, makanan.menu_makanan, makanan.harga_makanan,makanan.deskripsi_makanan, makanan.id_kategori, makanan.id_wilayah FROM kategori, makanan, wilayah Where makanan.id_kategori=kategori.id_kategori AND makanan.id_wilayah=wilayah.id_wilayah")->result();
     
       $this->response(array("status"=>"success","result" => $get_makanan));
   }

   //Menginputkan data pada pembelian
   function index_post() {
       $data_makanan = array(
           'id_makanan'   => $this->post('id_makanan'),
           'menu_makanan'     => $this->post('menu_makanan'),
           'harga_makanan'   => $this->post('harga_makanan'),
           'deskripsi_makanan'    => $this->post('deskripsi_makanan'),
    		   'id_kategori'       => $this->post('id_kategori'),
           'id_wilayah'       => $this->post('id_wilayah')
           );
      	//jika pada id_makanan tidak diisi maka tampilkan status
       if  (empty($data_makanan['id_makanan'])){
            $this->response(array('status'=>'fail',"message"=>"id makanan tidak boleh kosong"));
       }
       else {
           $getID = $this->db->query("Select id_makanan from makanan where id_makanan='".$data_makanan['id_makanan']."'")->result();
          
           //jika id_makanan tidak ada dalam database maka eksekusi insert
           if (empty($getID)){
                    if (empty($data_makanan['id_kategori'])){
                       $this->response(array('status'=>'fail',"message"=>"id kategori tidak boleh kosong"));
                    }
                    else if (empty($data_makanan['id_wilayah'])){
                       $this->response(array('status'=>'fail',"message"=>"id wilayah tidak boleh kosong"));
                    }
                    //jika salah satu kolom tidak diisi
                    else if(empty($data_makanan['menu_makanan'])){
                       $this->response(array('status'=>'fail',"message"=>"menu makanan tidak boleh kosong"));
                    }else if(empty($data_makanan['harga_makanan'])){
                       $this->response(array('status'=>'fail',"message"=>"harga makanan tidak boleh kosong"));
                    }else if(empty($data_makanan['deskripsi_makanan'])){
                       $this->response(array('status'=>'fail',"message"=>"deskripsi makanan tidak boleh kosong"));
                    }else{
                       //jika masuk pada else maka seluruh kondisi akan terpenuhi
                       //Selanjutnya akan mengecek id kategori dan id wilayah
                       $getIDKategori = $this->db->query("Select id_kategori from kategori Where id_kategori='".$data_makanan['id_kategori']."'")->result();
                       $getIDWilayah = $this->db->query("Select id_wilayah from wilayah Where id_wilayah='".$data_makanan['id_wilayah']."'")->result();
                       $message="";
                       if (empty($getIDKategori)) $message.="id kategori tidak sesuai ";
                       if (empty($getIDWilayah)) {
                           if (empty($message)) {
                               $message.="id wilayah tidak sesuai";
                           }
                           else {
                               $message.="dan id wilayah tidak sesuai";
                           }
                       }
                       //jika tidak ada pesan error
                       if (empty($message)){
                           $insert= $this->db->insert('makanan',$data_makanan);
                           if ($insert){
                               $this->response(array('status'=>'success','result' => $data_makanan,"message"=>$insert));   
                           }
                          
                       }else{
                           $this->response(array('status'=>'fail',"message"=>$message));   
                       }
                      
                    }
           }else{
               $this->response(array('status'=>'fail',"message"=>"id makanan sudah ada, mohon ganti id lain"));
           }  
       }
   }


   // edit data makanan
   function index_put() {
       $data_makanan = array(
           'id_makanan'   => $this->put('id_makanan'),
           'menu_makanan'     => $this->put('menu_makanan'),
           'harga_makanan'   => $this->put('harga_makanan'),
           'deskripsi_makanan'    => $this->put('deskripsi_makanan'),
           'id_kategori'       => $this->put('id_kategori'),
           'id_wilayah'       => $this->put('id_wilayah')
      );
       if  (empty($data_makanan['id_makanan'])){
            $this->response(array('status'=>'fail',"message"=>"id_makanan tidak boleh kosong"));
       }
       else {
           $getID = $this->db->query("Select id_makanan from makanan where id_makanan='".$data_makanan['id_makanan']."'")->result();
          
           //jika id_makanan tidak ada dalam database maka eksekusi insert
           if (empty($getID)){
             $this->response(array('status'=>'fail',"message"=>"id_makanan tidak sesuai, isi dengan benar")); 
           }
            
           else {
            if (empty($data_makanan['id_kategori'])){
                       $this->response(array('status'=>'fail',"message"=>"id kategori tidak boleh kosong"));
                    }
                    else if (empty($data_makanan['id_wilayah'])){
                       $this->response(array('status'=>'fail',"message"=>"id wilayah tidak boleh kosong"));
                    }
                    //jika salah satu kolom tidak diisi
                    else if(empty($data_makanan['menu_makanan'])){
                       $this->response(array('status'=>'fail',"message"=>"menu makanan tidak boleh kosong"));
                    }else if(empty($data_makanan['harga_makanan'])){
                       $this->response(array('status'=>'fail',"message"=>"harga makanan tidak boleh kosong"));
                    }else if(empty($data_makanan['deskripsi_makanan'])){
                       $this->response(array('status'=>'fail',"message"=>"deskripsi makanan tidak boleh kosong"));
                    }
                    else{
                      //jika masuk pada else maka seluruh kondisi akan terpenuhi
                      //Selanjutnya akan mengecek id kategori dan id wilayah
                      $getIDKategori = $this->db->query("Select id_kategori from kategori Where id_kategori='".$data_makanan['id_kategori']."'")->result();
                      $getIDWilayah = $this->db->query("Select id_wilayah from wilayah Where id_wilayah='".$data_makanan['id_wilayah']."'")->result();
                      $message="";
                      if (empty($getIDKategori)) $message.="id kategori tidak sesuai ";
                        if (empty($getIDWilayah)) {
                           if (empty($message)) {
                               $message.="id wilayah tidak sesuai";
                           }
                           else {
                               $message.="dan id wilayah tidak sesuai";
                           }
                        }
                        if (empty($message)){
                          $this->db->where('id_makanan',$data_makanan['id_makanan']);
                          $update= $this->db->update('makanan',$data_makanan);
                          if ($update){
                            $this->response(array('status'=>'success','result' => $data_makanan,"message"=>$update));
                          }
                      
                        }
                        else{
                          $this->response(array('status'=>'fail',"message"=>$message));   
                        }
                    }
           	  }
       	}
   }
   // delete makanan
   function index_delete() {
       $id_makanan = $this->delete('id_makanan');
       if (empty($id_makanan)){
           $this->response(array('status' => 'fail', "message"=>"id makanan harus diisi"));
       } else {
           $this->db->where('id_makanan', $id_makanan);
           $delete = $this->db->delete('makanan');  
           if ($this->db->affected_rows()) {
               $this->response(array('status' => 'success','message' =>"Berhasil hapus dengan id_makanan = ".$id_makanan));
           } else {
               $this->response(array('status' => 'fail', 'message' =>"id makanan tidak dalam database"));
           }
       }
   }
}

/* End of file Makanan.php */
/* Location: ./application/controllers/Makanan.php */