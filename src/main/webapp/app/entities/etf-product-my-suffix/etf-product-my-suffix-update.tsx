import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDealerParticipantMySuffix } from 'app/shared/model/dealer-participant-my-suffix.model';
import { getEntities as getDealerParticipants } from 'app/entities/dealer-participant-my-suffix/dealer-participant-my-suffix.reducer';
import { IMiMySuffix } from 'app/shared/model/mi-my-suffix.model';
import { getEntities as getMis } from 'app/entities/mi-my-suffix/mi-my-suffix.reducer';
import { IBankCustodyMySuffix } from 'app/shared/model/bank-custody-my-suffix.model';
import { getEntities as getBankCustodies } from 'app/entities/bank-custody-my-suffix/bank-custody-my-suffix.reducer';
import { getEntity, updateEntity, createEntity, reset } from './etf-product-my-suffix.reducer';
import { IEtfProductMySuffix } from 'app/shared/model/etf-product-my-suffix.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEtfProductMySuffixUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEtfProductMySuffixUpdateState {
  isNew: boolean;
  idsdealerParticipant: any[];
  miId: string;
  bankCustodyId: string;
}

export class EtfProductMySuffixUpdate extends React.Component<IEtfProductMySuffixUpdateProps, IEtfProductMySuffixUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idsdealerParticipant: [],
      miId: '0',
      bankCustodyId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getDealerParticipants();
    this.props.getMis();
    this.props.getBankCustodies();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { etfProductEntity } = this.props;
      const entity = {
        ...etfProductEntity,
        ...values,
        dealerParticipants: mapIdList(values.dealerParticipants)
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/etf-product-my-suffix');
  };

  render() {
    const { etfProductEntity, dealerParticipants, mis, bankCustodies, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="smartEtfApp.etfProduct.home.createOrEditLabel">Create or edit a EtfProduct</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : etfProductEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="etf-product-my-suffix-id">ID</Label>
                    <AvInput id="etf-product-my-suffix-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="productCodeLabel" for="etf-product-my-suffix-productCode">
                    Product Code
                  </Label>
                  <AvField
                    id="etf-product-my-suffix-productCode"
                    type="text"
                    name="productCode"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="productNameLabel" for="etf-product-my-suffix-productName">
                    Product Name
                  </Label>
                  <AvField
                    id="etf-product-my-suffix-productName"
                    type="text"
                    name="productName"
                    validate={{
                      maxLength: { value: 50, errorMessage: 'This field cannot be longer than 50 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="fundCategoryLabel" for="etf-product-my-suffix-fundCategory">
                    Fund Category
                  </Label>
                  <AvField
                    id="etf-product-my-suffix-fundCategory"
                    type="text"
                    name="fundCategory"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="inceptionDateLabel" for="etf-product-my-suffix-inceptionDate">
                    Inception Date
                  </Label>
                  <AvField id="etf-product-my-suffix-inceptionDate" type="date" className="form-control" name="inceptionDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="clearingHouseLabel" for="etf-product-my-suffix-clearingHouse">
                    Clearing House
                  </Label>
                  <AvField
                    id="etf-product-my-suffix-clearingHouse"
                    type="text"
                    name="clearingHouse"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="exchageLabel" for="etf-product-my-suffix-exchage">
                    Exchage
                  </Label>
                  <AvField
                    id="etf-product-my-suffix-exchage"
                    type="text"
                    name="exchage"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="unitCreationLabel" for="etf-product-my-suffix-unitCreation">
                    Unit Creation
                  </Label>
                  <AvField
                    id="etf-product-my-suffix-unitCreation"
                    type="string"
                    className="form-control"
                    name="unitCreation"
                    validate={{
                      min: { value: 0, errorMessage: 'This field should be at least 0.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="unitPriceInitialLabel" for="etf-product-my-suffix-unitPriceInitial">
                    Unit Price Initial
                  </Label>
                  <AvField id="etf-product-my-suffix-unitPriceInitial" type="string" className="form-control" name="unitPriceInitial" />
                </AvGroup>
                <AvGroup>
                  <Label id="unitPriceCurrLabel" for="etf-product-my-suffix-unitPriceCurr">
                    Unit Price Curr
                  </Label>
                  <AvField id="etf-product-my-suffix-unitPriceCurr" type="string" className="form-control" name="unitPriceCurr" />
                </AvGroup>
                <AvGroup>
                  <Label id="lotPerBasketLabel" for="etf-product-my-suffix-lotPerBasket">
                    Lot Per Basket
                  </Label>
                  <AvField id="etf-product-my-suffix-lotPerBasket" type="string" className="form-control" name="lotPerBasket" />
                </AvGroup>
                <AvGroup>
                  <Label id="indexReffLabel" for="etf-product-my-suffix-indexReff">
                    Index Reff
                  </Label>
                  <AvField
                    id="etf-product-my-suffix-indexReff"
                    type="text"
                    name="indexReff"
                    validate={{
                      maxLength: { value: 10, errorMessage: 'This field cannot be longer than 10 characters.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ratingLabel" for="etf-product-my-suffix-rating">
                    Rating
                  </Label>
                  <AvField id="etf-product-my-suffix-rating" type="string" className="form-control" name="rating" />
                </AvGroup>
                <AvGroup>
                  <Label id="feeLabel" for="etf-product-my-suffix-fee">
                    Fee
                  </Label>
                  <AvField id="etf-product-my-suffix-fee" type="string" className="form-control" name="fee" />
                </AvGroup>
                <AvGroup>
                  <Label for="etf-product-my-suffix-dealerParticipant">Dealer Participant</Label>
                  <AvInput
                    id="etf-product-my-suffix-dealerParticipant"
                    type="select"
                    multiple
                    className="form-control"
                    name="dealerParticipants"
                    value={etfProductEntity.dealerParticipants && etfProductEntity.dealerParticipants.map(e => e.id)}
                  >
                    <option value="" key="0" />
                    {dealerParticipants
                      ? dealerParticipants.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="etf-product-my-suffix-mi">Mi</Label>
                  <AvInput id="etf-product-my-suffix-mi" type="select" className="form-control" name="miId">
                    <option value="" key="0" />
                    {mis
                      ? mis.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="etf-product-my-suffix-bankCustody">Bank Custody</Label>
                  <AvInput id="etf-product-my-suffix-bankCustody" type="select" className="form-control" name="bankCustodyId">
                    <option value="" key="0" />
                    {bankCustodies
                      ? bankCustodies.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/etf-product-my-suffix" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  dealerParticipants: storeState.dealerParticipant.entities,
  mis: storeState.mi.entities,
  bankCustodies: storeState.bankCustody.entities,
  etfProductEntity: storeState.etfProduct.entity,
  loading: storeState.etfProduct.loading,
  updating: storeState.etfProduct.updating,
  updateSuccess: storeState.etfProduct.updateSuccess
});

const mapDispatchToProps = {
  getDealerParticipants,
  getMis,
  getBankCustodies,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EtfProductMySuffixUpdate);
