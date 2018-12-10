<?php

// use Restserver\Libraries\REST_Controller;

defined('BASEPATH') OR exit('No direct script access allowed');

// Jika ada pesan "REST_Controller not found"
require APPPATH . 'libraries/REST_Controller.php';
require APPPATH . 'libraries/Format.php';


class Makanan extends REST_Controller {

	private $folder_upload = 'uploads/';

    function all_get(){
        $get_makanan = $this->db->query("
            SELECT
                id_makanan,
                menu_makanan,
                harga_makanan,
                deskripsi_makanan,
                photo_url
				FROM makanan")->result();
       $this->response(
           array(
               "status" => "success",
               "result" => $get_makanan
           )
       );
    }

    function all_post() {

        $action  = $this->post('action');
        $data_makanan = array(
     	               'id_makanan' => $this->post('id_makanan'),
     	               'menu_makanan'       => $this->post('menu_makanan'),
     	               'harga_makanan'     => $this->post('harga_makanan'),
     	               'deskripsi_makanan'      => $this->post('deskripsi_makanan'),
     	               'photo_url'   => $this->post('photo_url')
 	               );

        switch ($action) {
            case 'insert':
                $this->insertMakanan($data_makanan);
                break;
            
            case 'update':
                $this->updateMakanan($data_makanan);
                break;
            
            case 'delete':
                $this->deleteMakanan($data_makanan);
                break;
            
            default:
                $this->response(
                    array(
                        "status"  =>"failed",
                        "message" => "action harus diisi"
                    )
                );
			break;
        }
    }

    function insertMakanan($data_makanan){

 	   // Cek validasi
 	   if (empty($data_makanan['menu_makanan']) || empty($data_makanan['harga_makanan']) || empty($data_makanan['deskripsi_makanan'])){
 	       $this->response(
 	           array(
 	               "status" => "failed",
 	               "message" => "menu_makanan / harga_makanan / deskripsi_makanan harus diisi"
 	           )
 	       );
 	   } else {

 	       $data_makanan['photo_url'] = $this->uploadPhoto();

 	       $do_insert = $this->db->insert('makanan', $data_makanan);
     	  
     	   if ($do_insert){
         	   $this->response(
         	       array(
         	           "status" => "success",
         	           "result" => array($data_makanan),
         	           "message" => $do_insert
         	       )
         	   );
            }
 	   }
    }

    function updateMakanan($data_makanan){

 	   // Cek validasi
 	   if (empty($data_makanan['menu_makanan']) || empty($data_makanan['harga_makanan']) || empty($data_makanan['deskripsi_makanan'])){
 	       $this->response(
 	           array(
 	           	"status" => "failed",
 	               "message" => "menu_makanan / harga_makanan / deskripsi_makanan harus diisi"
 	           )
 	       );
 	   } else {
 	       // Cek apakah ada di database
 	       $get_makanan_baseID = $this->db->query("
 	           SELECT 1
 	           FROM makanan
 	           WHERE id_makanan =  {$data_makanan['id_makanan']}")->num_rows();

 	       if($get_makanan_baseID === 0){
     	       // Jika tidak ada
     	       $this->response(
     	           array(
     	               "status"  => "failed",
     	               "message" => "ID makanan tidak ditemukan"
     	           )
     	       );
 	       } else {

               //  // Jika ada
                               
               // // Cek apakah user upload photo dalam update ini
               // if ( isset($_FILES['photo_url']) && $_FILES['photo_url']['size'] > 0 ) {

               //     // Cek apakah ada photo di data sebelumnya
               //     $get_photo_url =$this->db->query("
               //         SELECT photo_url
               //         FROM makanan
               //         WHERE id_makanan = {$data_makanan['id_makanan']}")->result(); 

               //      if(!empty($get_photo_url)){
               //          // Dapatkan nama file
               //          $photo_nama_file = basename($get_photo_url[0]->photo_url);
               //          // Dapatkan letak file di folder upload
               //          $photo_lokasi_file = realpath(FCPATH . $this->folder_upload . $photo_nama_file);
                        
               //          // Jika file ada, hapus
               //          if(file_exists($photo_lokasi_file)) {
               //              // Hapus file
               //             unlink($photo_lokasi_file);
               //         }
               //     }
               //     $update_photo = true;
               //     // Lakukan upload photo
               //     $data_makanan['photo_url'] = $this->uploadPhoto();
               // } else {
               //     $update_photo = false;
               // }

 	           // Jika ada
 	           $data_makanan['photo_url'] = $this->uploadPhoto();

         	   if ($data_makanan['photo_url']){
         	       // Jika upload foto berhasil, eksekusi update
         	       $update = $this->db->query("
         	           UPDATE makanan SET
         	               menu_makanan = '{$data_makanan['menu_makanan']}',
         	               harga_makanan = '{$data_makanan['harga_makanan']}',
         	               deskripsi_makanan = '{$data_makanan['deskripsi_makanan']}',
         	               photo_url = '{$data_makanan['photo_url']}'
         	           WHERE id_makanan = '{$data_makanan['id_makanan']}'");

         	   } else {
         	       // Jika foto kosong atau upload foto tidak berhasil, eksekusi update
                    $update = $this->db->query("
                        UPDATE makanan
                        SET
                            menu_makanan    = '{$data_makanan['menu_makanan']}',
                            harga_makanan  = '{$data_makanan['harga_makanan']}',
                            deskripsi_makanan    = '{$data_makanan['deskripsi_makanan']}'
                        WHERE id_makanan = {$data_makanan['id_makanan']}"
                    );
         	   }
         	  
         	   if ($update){
             	   $this->response(
             	       array(
             	           "status"    => "success",
             	           "result"    => array($data_makanan),
             	           "message"   => $update
             	       )
             	   );
                }
 	       }   
 	   }
    }

    function deleteMakanan($data_makanan){

        if (empty($data_makanan['id_makanan'])){
 	       $this->response(
 	           array(
 	               "status" => "failed",
 	               "message" => "ID makanan harus diisi"
 	           )
 	       );
 	   } else {
 	       // Cek apakah ada di database
 	       $get_makanan_baseID =$this->db->query("
 	           SELECT 1
 	           FROM makanan
 	           WHERE id_makanan = {$data_makanan['id_makanan']}")->num_rows();

 	       if($get_makanan_baseID > 0){
 	           
 	           $get_photo_url =$this->db->query("
 	           SELECT photo_url
 	           FROM makanan
 	           WHERE id_makanan = {$data_makanan['id_makanan']}")->result();
 	       
                if(!empty($get_photo_url)){

                    // Dapatkan menu_makanan file
                    $photo_menu_makanan_file = basename($get_photo_url[0]->photo_url);
                    // Dapatkan letak file di folder upload
                    $photo_lokasi_file = realpath(FCPATH . $this->folder_upload . $photo_menu_makanan_file);
                    
                    // Jika file ada, hapus
                    if(file_exists($photo_lokasi_file)) {
                        // Hapus file
         	           unlink($photo_lokasi_file);
         	       }

         	       $this->db->query("
         	           DELETE FROM makanan
         	           WHERE id_makanan = {$data_makanan['id_makanan']}");
         	       $this->response(
         	           array(
         	               "status" => "success",
         	               "message" => "Data ID = " .$data_makanan['id_makanan']. " berhasil dihapus"
         	           )
         	       );
         	   }
 	       
            } else {
                $this->response(
                    array(
                        "status" => "failed",
                        "message" => "ID makanan tidak ditemukan"
                    )
                );
            }
 	   }
    }

    function uploadPhoto() {

    	// Apakah user upload gambar?
        if ( isset($_FILES['photo_url']) && $_FILES['photo_url']['size'] > 0 ){

            // Foto disimpan di android-api/uploads
            $config['upload_path'] = realpath(FCPATH . $this->folder_upload);
            $config['allowed_types'] = 'jpg|png';

 	       // Load library upload & helper
 	       $this->load->library('upload', $config);
 	       $this->load->helper('url');

 	       // Apakah file berhasil diupload?
 	       if ( $this->upload->do_upload('photo_url')) {

               // Berhasil, simpan menu_makanan file-nya
               // URL image yang disimpan adalah http://localhost/android-api/uploads/menu_makananfile
        	   $img_data = $this->upload->data();
        	   $post_image = base_url(). $this->folder_upload .$img_data['file_name'];

 	       } else {

 	           // Upload gagal, beri menu_makanan image dengan errornya
 	           // Ini bodoh, tapi efektif
 	           $post_image = $this->upload->display_errors();
 	           
 	       }
 	   } else {
 	       // Tidak ada file yang di-upload, kosongkan menu_makanan image-nya
 	       $post_image = '';
 	   }

 	   return $post_image;
    }
}

/* End of file makanan.php */
/* Location: ./application/controllers/makanan.php */