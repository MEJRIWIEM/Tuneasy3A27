<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210303211930 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->abortIf($this->connection->getDatabasePlatform()->getName() !== 'mysql', 'Migration can only be executed safely on \'mysql\'.');

        $this->addSql('CREATE TABLE reservation (id INT AUTO_INCREMENT NOT NULL, logement_id INT DEFAULT NULL, prix_total DOUBLE PRECISION NOT NULL, date_check_in DATETIME NOT NULL, date_check_out DATETIME NOT NULL, INDEX IDX_42C8495558ABF955 (logement_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ENGINE = InnoDB');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C8495558ABF955 FOREIGN KEY (logement_id) REFERENCES logement (id)');
        $this->addSql('ALTER TABLE logement MODIFY id_logement INT NOT NULL');
        $this->addSql('ALTER TABLE logement DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE logement CHANGE id_logement id INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE logement ADD PRIMARY KEY (id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->abortIf($this->connection->getDatabasePlatform()->getName() !== 'mysql', 'Migration can only be executed safely on \'mysql\'.');

        $this->addSql('DROP TABLE reservation');
        $this->addSql('ALTER TABLE logement MODIFY id INT NOT NULL');
        $this->addSql('ALTER TABLE logement DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE logement CHANGE id id_logement INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE logement ADD PRIMARY KEY (id_logement)');
    }
}
