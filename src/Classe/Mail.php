<?php
namespace App\Classe;
use Mailjet\Client;
use Mailjet\Resources;

class Mail
{
    private $api_Key='0e5a6f1525e90b998cb6b26d58ad0ae3';
    private $api_Key_secret='9a62522cc739d30930ebd68588b95961';

    public function send($to_email,$to_name,$subject,$content)
{
    $mj= new Client($this->api_Key,$this->api_Key_secret, true,['version'=>'v3.1']);

$body = [
    'Messages' => [
        [
            'From' => [
                'Email' => "youssef.brinsi@esprit.tn",
                'Name' => "Youssef"
            ],
            'To' => [
                [
                    'Email' => $to_email,
                    'Name' => $to_name
                ]
            ],
            'TemplateID' => 2624202,
            'TemplateLanguage' => true,
            'Subject' => $subject,
            'Variables' => [
                'content' => $content

            ]
        ]
    ]
];
$response = $mj->post(Resources::$Email, ['body' => $body]);
$response->success();
}

}